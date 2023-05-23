package com.example.cadastrodeclientes.pressentation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.cadastrodeclientes.AdapterAluno
import com.example.cadastrodeclientes.Data.Aluno
import com.example.cadastrodeclientes.Data.AppDataBase
import com.example.cadastrodeclientes.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.Serializable

class Alunos_list : AppCompatActivity() {


    // Variável preguiçosa (lazy) para inicializar o AdapterAluno
    private val adapter: AdapterAluno by lazy {
        AdapterAluno(::onListItemClicked)
    }

    private  val dataBase by lazy {
         Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "Alunos-database"
        ).build()
    }
    private val daoAluno by lazy { dataBase.DAOAluno() }


    // adapter
    private val startForResultAluno = registerForActivityResult(

        ActivityResultContracts.StartActivityForResult()
    )
    { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {

            //Pegando Resultado
            val data = result.data
            val taskAction = data?.getSerializableExtra(ALUNO_ACTION_RESULT) as AlunoAction?
            val aluno:Aluno = taskAction!!.aluno


        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos_list)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        listFromDataBase()
        // Configura o RecyclerView
        val rvAluno: RecyclerView = findViewById(R.id.rv_student_list)
        rvAluno.adapter = adapter

        // Configura o botão de adicionar aluno
        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener {
            openAlunoListDetail(null)
        }
    }

    private fun execute(alunoAction: AlunoAction?) {
        val aluno = alunoAction?.aluno ?: return
        when (ActionTypeAluno.valueOf(alunoAction?.actiontype?:"")) {
            ActionTypeAluno.DELETE -> deleteById(aluno.id)
            ActionTypeAluno.CREATE -> insertIntoDataBase(aluno)
            ActionTypeAluno.UPDATE -> updateIntoDataBase(aluno)
        }
    }

    private fun listFromDataBase() {
        CoroutineScope(IO).launch {
            val myDataBaseList: List<Aluno> = daoAluno.getAll()
            adapter.submitList(myDataBaseList)
        }
    }

    private fun insertIntoDataBase(aluno: Aluno) {
        CoroutineScope(IO).launch {
            daoAluno.insert(aluno)
            listFromDataBase()
        }
    }

    private fun deleteAll() {
        CoroutineScope(IO).launch {
            daoAluno.deleteAll()
            listFromDataBase()
        }
    }

    private fun deleteById(id: Int) {
        CoroutineScope(IO).launch {
            daoAluno.deleteById(id)
            listFromDataBase()
        }
    }

    private fun updateIntoDataBase(aluno: Aluno) {
        CoroutineScope(IO).launch {
            daoAluno.update(aluno)
            listFromDataBase()
        }
    }

    private fun onListItemClicked(aluno: Aluno) {
        openAlunoListDetail(aluno)
    }

    private fun openAlunoListDetail(aluno: Aluno?) {
        val intent = AlunosDetail.start(this, aluno)
        startForResultAluno.launch(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.alunolistmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_aluno_list -> {
                deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }
}



enum class ActionTypeAluno {


    DELETE,
    UPDATE,
    CREATE

}

data class AlunoAction(
    val aluno: Aluno,
    val actiontype: String

) : Serializable


const val ALUNO_ACTION_RESULT = "ALUNO_ACTION_RESULT"
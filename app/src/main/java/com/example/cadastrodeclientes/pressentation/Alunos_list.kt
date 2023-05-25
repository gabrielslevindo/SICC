package com.example.cadastrodeclientes.pressentation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastrodeclientes.AdapterAluno
import com.example.cadastrodeclientes.Data.Aluno
import com.example.cadastrodeclientes.Data.AppDataBase
import com.example.cadastrodeclientes.R
import com.example.cadastrodeclientes.SIECAplication
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable

class Alunos_list : AppCompatActivity() {


    // Variável preguiçosa (lazy) para inicializar o AdapterAluno
    private val adapter: AdapterAluno by lazy {
        AdapterAluno(::onListItemClickedAluno)
    }

    lateinit var dataBase: AppDataBase


    private val viewModel: AlunoViewModel by lazy {

        AlunoViewModel.create(application)
    }


    // adapter
    private val startForResultAluno = registerForActivityResult(

        ActivityResultContracts.StartActivityForResult()
    )
    { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {

            //Pegando Resultado
            val data = result.data
            val alunoAction = data?.getSerializableExtra(ALUNO_ACTION_RESULT) as AlunoAction?
            val aluno: Aluno? = alunoAction!!.aluno
            viewModel.execute(alunoAction)


        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos_list)

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

    override fun onStart() {
        super.onStart()

        dataBase = (application as SIECAplication).getAppDataBase()

        listFromDataBase()

    }



    private fun deleteAll() {
        val alunoAction = AlunoAction(null, ActionTypeAluno.DELETE_ALL.name)

        viewModel.execute(alunoAction)
    }

    private fun listFromDataBase() {
        val listObserverAluno = Observer<List<Aluno>> { listAluno ->
            //obeserver

            adapter.submitList(listAluno)


        }
        viewModel.AlunoListLiveData.observe(this@Alunos_list, listObserverAluno)


    }

    private fun onListItemClickedAluno(aluno: Aluno) {
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
    DELETE_ALL,
    UPDATE,
    CREATE

}

data class AlunoAction(
    val aluno: Aluno?,
    val actiontype: String

) : Serializable


const val ALUNO_ACTION_RESULT = "ALUNO_ACTION_RESULT"
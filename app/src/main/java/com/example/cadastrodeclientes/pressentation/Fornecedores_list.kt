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
import com.example.cadastrodeclientes.AdapterFornecedor
import com.example.cadastrodeclientes.Data.AppDataBase
import com.example.cadastrodeclientes.Data.Fornecedor
import com.example.cadastrodeclientes.R
import com.example.cadastrodeclientes.SIECAplication
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable

class Fornecedores_list : AppCompatActivity() {

    // Variável preguiçosa (lazy) para inicializar o AdapterAluno
    private val adapter: AdapterFornecedor by lazy {
        AdapterFornecedor(::onListItemClickedFornecedor)
    }


    lateinit var dataBase: AppDataBase


    private val viewModel: FornecedorViewModel by lazy {

        FornecedorViewModel.create(application)
    }


    private val startForResultFornecedor = registerForActivityResult(

        ActivityResultContracts.StartActivityForResult()
    )
    { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {

            //Pegando Resultado
            val data = result.data
            val fornecedorAction = data?.getSerializableExtra(FORNECEDOR_ACTION_RESULT) as FornecedorAction?
            val fornecedor: Fornecedor? = fornecedorAction!!.fornecedor
            viewModel.execute(fornecedorAction)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fornecedores_list)



        listFromDataBase()


        // Configura o RecyclerView
        val rvFornecedor: RecyclerView = findViewById(R.id.rv_supplier_list)
        rvFornecedor.adapter = adapter

        // Configura o botão de adicionar aluno
        val fab = findViewById<FloatingActionButton>(R.id.fab_add_forne)
        fab.setOnClickListener {
            openFonecedorListDetail(null)
        }
    }


    override fun onStart() {
        super.onStart()

        dataBase = (application as SIECAplication).getAppDataBase()

        listFromDataBase()

    }




    private fun deleteAll() {
        val fornecedorAction = FornecedorAction(null, ActionTypeFornecedor.DELETE_ALL.name)

        viewModel.execute(fornecedorAction)
    }

    private fun listFromDataBase() {
        val listObserverFonecedor = Observer<List<Fornecedor>> { listFornecedor ->
            //obeserver

            adapter.submitList(listFornecedor)


        }
        viewModel.FornecedorListLiveData.observe(this@Fornecedores_list, listObserverFonecedor)


    }

    private fun onListItemClickedFornecedor(fornecedor: Fornecedor) {
        openFonecedorListDetail(fornecedor)
    }

    private fun openFonecedorListDetail(fornecedor: Fornecedor?) {
        val intent = FornecedoresDetail.start(this, fornecedor)
        startForResultFornecedor.launch(intent)
    }



    // MENU DELETAR TODAS
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.fornecedordetailmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_fornecedor -> {
                deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}

enum class ActionTypeFornecedor {

    DELETE,
    DELETE_ALL,
    UPDATE,
    CREATE

}

data class FornecedorAction(
    val fornecedor: Fornecedor?,
    val actiontype: String

) : Serializable


const val FORNECEDOR_ACTION_RESULT = "FORNECEDOR_ACTION_RESULT "
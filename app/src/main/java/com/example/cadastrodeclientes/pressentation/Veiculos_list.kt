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
import com.example.cadastrodeclientes.AdapterVeiculo
import com.example.cadastrodeclientes.Data.AppDataBase
import com.example.cadastrodeclientes.Data.Veiculo
import com.example.cadastrodeclientes.R
import com.example.cadastrodeclientes.SIECAplication
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable

class Veiculos_list : AppCompatActivity() {


    // Variável preguiçosa (lazy) para inicializar o AdapterAluno
    private val adapter: AdapterVeiculo by lazy {
        AdapterVeiculo(::onListItemClickedVeiculo)
    }

    lateinit var dataBase: AppDataBase


    private val viewModel: VeiculoViewModel by lazy {

        VeiculoViewModel.create(application)
    }
    private val startForResultVeiculo = registerForActivityResult(

        ActivityResultContracts.StartActivityForResult()
    )
    { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {

            //Pegando Resultado
            val data = result.data
            val veiculoAction = data?.getSerializableExtra(VEICULO_ACTION_RESULT) as VeiculosAction?
            val veiculo: Veiculo? = veiculoAction!!.veiculos
            viewModel.execute(veiculoAction)


        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_veiculos_list)



        listFromDataBase()


        // Configura o RecyclerView
        val rvVeiculo: RecyclerView = findViewById(R.id.rv_veicle_list)
        rvVeiculo.adapter = adapter

        // Configura o botão de adicionar aluno
        val fab = findViewById<FloatingActionButton>(R.id.fab_add_v)
        fab.setOnClickListener {
            openVeiculoListDetail(null)
        }
    }

    override fun onStart() {
        super.onStart()

        dataBase = (application as SIECAplication).getAppDataBase()

        listFromDataBase()

    }

    private fun deleteAll() {
        val veiculoAction = VeiculosAction(null, ActionTypeVeiculos.DELETE_ALL.name)

        viewModel.execute(veiculoAction)
    }

    private fun listFromDataBase() {
        val listObserverVeiculos = Observer<List<Veiculo>> { listVeiculo ->
            //obeserver

            adapter.submitList(listVeiculo)


        }
        viewModel.VeiculoListLiveData.observe(this@Veiculos_list, listObserverVeiculos)


    }

    private fun onListItemClickedVeiculo(veiculo: Veiculo) {
        openVeiculoListDetail(veiculo)
    }

    private fun openVeiculoListDetail(veiculo: Veiculo?) {
        val intent = VeiculosDetail.start(this, veiculo)
        startForResultVeiculo.launch(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.veiculodetailmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_veiculo -> {
                deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}




enum class ActionTypeVeiculos {

    DELETE,
    DELETE_ALL,
    UPDATE,
    CREATE

}

data class VeiculosAction(
    val veiculos: Veiculo?,
    val actiontype: String

) : Serializable


const val VEICULO_ACTION_RESULT = "VEICULO_ACTION_RESULT "
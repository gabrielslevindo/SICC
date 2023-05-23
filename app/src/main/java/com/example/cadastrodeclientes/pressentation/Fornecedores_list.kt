package com.example.cadastrodeclientes.pressentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.cadastrodeclientes.Data.Aluno
import com.example.cadastrodeclientes.Data.Fornecedor
import com.example.cadastrodeclientes.R
import java.io.Serializable

class Fornecedores_list : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fornecedores_list)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

















    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)}
}


enum class ActionTypeFornecedor {


    DELETE,
    UPDATE,
    CREATE

}

data class FornecedorAction(
    val fornecedor: Fornecedor,
    val actiontype: String

) : Serializable


const val FORNECEDOR_ACTION_RESULT = "FORNECEDOR_ACTION_RESULT "
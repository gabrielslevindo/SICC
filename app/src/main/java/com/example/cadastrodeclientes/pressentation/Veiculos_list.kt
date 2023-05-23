package com.example.cadastrodeclientes.pressentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.cadastrodeclientes.Data.Fornecedor
import com.example.cadastrodeclientes.Data.Veiculo
import com.example.cadastrodeclientes.R
import java.io.Serializable

class Veiculos_list : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_veiculos_list)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)










    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)}
}


enum class ActionTypeVeiculos {


    DELETE,
    UPDATE,
    CREATE

}

data class VeiculosAction(
    val veiculos:Veiculo,
    val actiontype: String

) : Serializable


const val VEICULO_ACTION_RESULT = "VEICULO_ACTION_RESULT "
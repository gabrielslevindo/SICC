package com.example.cadastrodeclientes.pressentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.cadastrodeclientes.Data.Veiculo
import com.example.cadastrodeclientes.R
import com.google.android.material.snackbar.Snackbar

class VeiculosDetail : AppCompatActivity() {


    // var aluno para ficar disponivel quando necessário
    private var veiculo : Veiculo? = null

    //var do botão pra ficar disponpível quando necessário
    private lateinit var btnDoneVeiculo: Button


    companion object {
        // responsabilidade dessa tela de jogar a view para tela anterior

        private const val VEICULO_DETAIL_EXTRA = "veiculo.extra.detail"

        fun start(context: Context, veiculo: Veiculo?): Intent {


            val intent = Intent(context, VeiculosDetail::class.java)

                .apply {

                    putExtra(VEICULO_DETAIL_EXTRA, veiculo)

                }

            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_veiculos_detail)


        //recuperar string da tela anterior.
        veiculo = intent.getSerializableExtra(VEICULO_DETAIL_EXTRA) as Veiculo?


        // Aqui estou recuperando os edit text para descrição e para titulo da tarefa.
        val edtMarca = findViewById<EditText>(R.id.edt_marca)
        val edtModelo = findViewById<EditText>(R.id.edt_modelo)
        val edtAno = findViewById<EditText>(R.id.edt_ano)
        val edtPlaca = findViewById<EditText>(R.id.edt_placa)

        //Aqui recupero o id do botão
        btnDoneVeiculo = findViewById<Button>(R.id.btn_done_veiculo)

        if(veiculo != null){ //if para dizer que os atributos tem que ser diferentes de nulo
            edtMarca.setText(veiculo!!.brand)
            edtModelo.setText(veiculo!!.model)
            edtAno.setText(veiculo!!.year)
            edtPlaca.setText(veiculo!!.plate)
        }

        //ação click do botão
        btnDoneVeiculo.setOnClickListener {

            val brand = edtMarca.text.toString()
            val model = edtModelo.text.toString()
            val year = edtAno.text.toString()
            val plate = edtPlaca.text.toString()


            if (brand.isNotEmpty() && model.isNotEmpty() && year.isNotEmpty() && plate.isNotEmpty() ){

                if(veiculo == null){
                    //tarefa igual a vazio
                    //Cria a nova tarefa
                    addOrUpdateVeiculo(0,brand,model,year,plate, ActionTypeVeiculos.CREATE)

                }else {
                    //atualizar a tarefa task.id é para pegar o id da tarefa anterior
                    addOrUpdateVeiculo(veiculo!!.id,brand,model,year,plate,ActionTypeVeiculos.UPDATE)

                }

            }else {

                showMessage(it, "Filds are requered")
            }
        }
    }




    //Aqui é a função de atualizar ou atualizar a tarefa, no if e else abaixo de btn done chamamos ela.
    private  fun addOrUpdateVeiculo(id:Int,brand: String, model: String,year: String,plate:String, actionTypeV: ActionTypeVeiculos) {

        val veiculo = Veiculo   (id,brand, model, year, plate)
        returnAction(veiculo, actionTypeV)


    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // aqui estamos inflando o botão pra conseguir utilizar ele

        val inflater: MenuInflater = menuInflater

        inflater.inflate(R.menu.veiculodetailmenu, menu)

        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) { // aqui é para setar a ação de deletar
            R.id.delete_veiculo -> {
                //Código para sretar o resultado na tela anterior.
                if (veiculo!= null) {
                    returnAction(veiculo!!, ActionTypeVeiculos.DELETE)
                } else {
                    showMessage(btnDoneVeiculo, "Item not found")
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        } }



    private fun returnAction(veiculo: Veiculo, actionType: ActionTypeVeiculos) { // retornar ação

        val intent = Intent()
            .apply {
                val veiculoAction = VeiculosAction(veiculo!!, actionType.name)
                putExtra(VEICULO_ACTION_RESULT, veiculoAction)
            }
        setResult(Activity.RESULT_OK, intent)
        finish()

    }

}



//mensagem de validação
private fun showMessage(view: View, message: String) {

    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        .setAction("Action", null)
        .show()

}


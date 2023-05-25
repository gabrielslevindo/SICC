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
import com.example.cadastrodeclientes.Data.Fornecedor
import com.example.cadastrodeclientes.R
import com.google.android.material.snackbar.Snackbar

class FornecedoresDetail : AppCompatActivity() {

    // var aluno para ficar disponivel quando necessário
    private var fornecedor: Fornecedor? = null

    //var do botão pra ficar disponpível quando necessário
    private lateinit var btnDoneFornecedor: Button


    companion object {
        // responsabilidade dessa tela de jogar a view para tela anterior

        private const val FORNECEDOR_DETAIL_EXTRA = "fornecedor.extra.detail"

        fun start(context: Context, fornecedor: Fornecedor?): Intent {


            val intent = Intent(context, FornecedoresDetail::class.java)

                .apply {

                    putExtra(FORNECEDOR_DETAIL_EXTRA, fornecedor)

                }

            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fornecedores_detail)



        //recuperar string da tela anterior.
        fornecedor = intent.getSerializableExtra(FORNECEDOR_DETAIL_EXTRA) as Fornecedor?


        // Aqui estou recuperando os edit text para descrição e para titulo da tarefa.
        val edtNome = findViewById<EditText>(R.id.edt_nome_fornecedor)
        val edtEmpresa = findViewById<EditText>(R.id.edt_empresa)
        val edtTelefoneF = findViewById<EditText>(R.id.edt_telefone_fornecedor)
        val edtEnderecoF = findViewById<EditText>(R.id.edt_endereco_fornecedor)

        //Aqui recupero o id do botão
        btnDoneFornecedor = findViewById<Button>(R.id.btn_done_fornecedor)

        if(fornecedor != null){ //if para dizer que os atributos tem que ser diferentes de nulo
            edtNome.setText(fornecedor!!.name)
            edtEmpresa.setText(fornecedor!!.company)
            edtTelefoneF.setText(fornecedor!!.telephone)
            edtEnderecoF.setText(fornecedor!!.address)
        }

        //ação click do botão
        btnDoneFornecedor.setOnClickListener {

            val name = edtNome.text.toString()
            val company = edtEmpresa.text.toString()
            val telephoneF = edtTelefoneF.text.toString()
            val addressF = edtEnderecoF.text.toString()


            if (name.isNotEmpty() && company.isNotEmpty() && telephoneF.isNotEmpty() && addressF.isNotEmpty() ){

                if(fornecedor == null){
                    //tarefa igual a vazio
                    //Cria a nova tarefa
                    addOrUpdateFornecedor(0,name,company,telephoneF,addressF, ActionTypeFornecedor.CREATE)

                }else {
                    //atualizar a tarefa task.id é para pegar o id da tarefa anterior
                    addOrUpdateFornecedor(fornecedor!!.id,name,company,telephoneF,addressF,ActionTypeFornecedor.UPDATE)

                }

            }else {

                showMessage(it, "Filds are requered")
            }
        }
    }




    //Aqui é a função de atualizar ou atualizar a tarefa, no if e else abaixo de btn done chamamos ela.
    private  fun addOrUpdateFornecedor(id:Int,name: String, company: String,telephoneF: String,adddressF:String, actionTypeF: ActionTypeFornecedor) {

        val fornecedor = Fornecedor  (id,name, company, telephoneF, adddressF)
        returnAction(fornecedor, actionTypeF)


    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // aqui estamos inflando o botão pra conseguir utilizar ele

        val inflater: MenuInflater = menuInflater

        inflater.inflate(R.menu.fornecedordetailmenu, menu)

        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) { // aqui é para setar a ação de deletar
            R.id.delete_fornecedor -> {
                //Código para sretar o resultado na tela anterior.
                if (fornecedor!= null) {
                    returnAction(fornecedor!!, ActionTypeFornecedor.DELETE)
                } else {
                    showMessage(btnDoneFornecedor, "Item not found")
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        } }



    private fun returnAction(fornecedor: Fornecedor, actionType: ActionTypeFornecedor) { // retornar ação

        val intent = Intent()
            .apply {
                val fornecedorAction = FornecedorAction(fornecedor!!, actionType.name)
                putExtra(FORNECEDOR_ACTION_RESULT, fornecedorAction)
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
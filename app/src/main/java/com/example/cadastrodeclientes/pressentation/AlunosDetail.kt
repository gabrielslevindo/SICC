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
import com.example.cadastrodeclientes.Data.Aluno
import com.example.cadastrodeclientes.R
import com.google.android.material.snackbar.Snackbar

class AlunosDetail : AppCompatActivity() {

    // var aluno para ficar disponivel quando necessário
    private var aluno: Aluno? = null
    //var do botão pra ficar disponpível quando necessário
    private lateinit var btnDoneAluno: Button



    companion object {
        // responsabilidade dessa tela de jogar a view para tela anterior

        private const val ALUNO_DETAIL_EXTRA = "aluno.extra.detail"

        fun start(context: Context, aluno: Aluno?): Intent {


            val intent = Intent(context, AlunosDetail::class.java)

                .apply {

                    putExtra(ALUNO_DETAIL_EXTRA, aluno)

                }

            return intent
        }
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos_detail)


        //recuperar string da tela anterior.
        aluno = intent.getSerializableExtra(ALUNO_DETAIL_EXTRA) as Aluno?


        // Aqui estou recuperando os edit text para descrição e para titulo da tarefa.
        val edtNome = findViewById<EditText>(R.id.edt_nome_aluno)
        val edtMatricula = findViewById<EditText>(R.id.edt_matricula)
        val edtTelefone = findViewById<EditText>(R.id.edt_telefone)
        val edtEndereco = findViewById<EditText>(R.id.edt_endereco)

        //Aqui recupero o id do botão
        btnDoneAluno = findViewById<Button>(R.id.btn_done_aluno)

        if(aluno != null){ //if para dizer que os atributos tem que ser diferentes de nulo
            edtNome.setText(aluno!!.name)
            edtMatricula.setText(aluno!!.registration)
            edtTelefone.setText(aluno!!.telephone)
            edtEndereco.setText(aluno!!.address)
        }

        //ação click do botão
        btnDoneAluno.setOnClickListener {

            val name = edtNome.text.toString()
            val registration = edtMatricula.text.toString()
            val telephone = edtTelefone.text.toString()
            val address = edtEndereco.text.toString()


            if (name.isNotEmpty() && registration.isNotEmpty() && telephone.isNotEmpty() && address.isNotEmpty() ){

                if(aluno == null){
                    //tarefa igual a vazio
                    //Cria a nova tarefa
                    addOrUpdateAluno(0,name,registration,telephone,address, ActionTypeAluno.CREATE)

                }else {
                    //atualizar a tarefa task.id é para pegar o id da tarefa anterior
                    addOrUpdateAluno(aluno!!.id,name,registration,telephone,address,ActionTypeAluno.UPDATE)

                }

            }else {

                showMessage(it, "Filds are requered")
            }
        }
    }




    //Aqui é a função de atualizar ou atualizar a tarefa, no if e else abaixo de btn done chamamos ela.
    private  fun addOrUpdateAluno(id:Int,name: String, registration: String,telephone: String,adddress:String, actionType: ActionTypeAluno) {

        val aluno =  Aluno (id,name, registration, telephone, adddress)
        returnAction(aluno, actionType)


    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // aqui estamos inflando o botão pra conseguir utilizar ele

        val inflater: MenuInflater = menuInflater

        inflater.inflate(R.menu.alunodetailmenu, menu)

        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) { // aqui é para setar a ação de deletar
            R.id.delete_aluno -> {
                //Código para sretar o resultado na tela anterior.
                if (aluno != null) {
                    returnAction(aluno!!, ActionTypeAluno.DELETE)
                } else {
                    showMessage(btnDoneAluno, "Item not found")
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        } }



    private fun returnAction(aluno: Aluno, actionType: ActionTypeAluno) { // retornar ação

        val intent = Intent()
            .apply {
                val alunoAction = AlunoAction(aluno!!, actionType.name)
                putExtra(ALUNO_ACTION_RESULT, alunoAction)
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

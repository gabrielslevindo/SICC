package com.example.cadastrodeclientes.pressentation

//SIEC - SISTEMA INTEGRADO DE CREDENCIAMENTO E CADASTRO

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cadastrodeclientes.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login = findViewById<Button>(R.id.BtnLogin)
        login.setOnClickListener {

            val eemail = findViewById<EditText>(R.id.EdtEmail)
            val esenha = findViewById<EditText>(R.id.EdtSenha)
            val email = eemail.text.toString()
            val senha = esenha.text.toString()

            if(email == "gabrielslevindo" && senha == "Mas052202") {


                showMensage("Login Feito com sucesso.")

                val intent = Intent(applicationContext, LoginUser::class.java)


                //aqui nessa intent eu utilizei o parametronome ou poderia ter usado qualquer outro nonme, depois eu peguei o usuário que no caso é o eemail e extendi o text e o tostring como abaixo:
                intent.putExtra("ParametroNome", eemail.text.toString() )
                startActivity(intent)


            }else{

                showMensage("Login e/ou senha invalidos.")

            }

        }

    }


    private   fun showMensage(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }
}




package com.example.cadastrodeclientes.pressentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.cadastrodeclientes.R

class LoginUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)
        val tvResult: TextView = findViewById(R.id.tvSegundaTela)
        val result = intent.getStringExtra("ParametroNome")

        tvResult.text = "Prezado $result, seja bem-vindo!"

        val btnAluno = findViewById<Button>(R.id.btn_aluno)
        val btnFornecedor = findViewById<Button>(R.id.btn_fornecedor)
        val btnVeiculo = findViewById<Button>(R.id.btn_veiculo)

        btnAluno.setOnClickListener {
            val intent = Intent(this, Alunos_list::class.java)
            startActivity(intent)
        }
        btnFornecedor.setOnClickListener {
            val intent = Intent(this, Fornecedores_list::class.java)
            startActivity(intent)
        }
        btnVeiculo.setOnClickListener {
            val intent = Intent(this, Veiculos_list::class.java)
            startActivity(intent)
        }


    }



}



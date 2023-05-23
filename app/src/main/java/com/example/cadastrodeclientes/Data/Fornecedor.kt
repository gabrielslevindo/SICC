package com.example.cadastrodeclientes.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

   @Entity
    data class Fornecedor(
            @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name: String,
        val company:String,
        val telephone: String,
        val address:String



    ): Serializable




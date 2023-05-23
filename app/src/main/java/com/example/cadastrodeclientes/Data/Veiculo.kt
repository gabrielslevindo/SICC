package com.example.cadastrodeclientes.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
      @Entity
    data class Veiculo(
             @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val brand: String,
        val model:String,
        val year:String,
        val plate: String

    ): Serializable


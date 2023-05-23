package com.example.cadastrodeclientes.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

  @Entity
    data class Aluno(
      @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name: String,
        val registration:String,
        val telephone: String,
        val address:String

    ): Serializable




package com.example.cadastrodeclientes.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Aluno::class, Veiculo::class,Fornecedor::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun DAOAluno():DAOAluno
    abstract fun DAOVeiculos():DAOVeiculos
    abstract fun DAOFornecedores():DAOFornecedores
}


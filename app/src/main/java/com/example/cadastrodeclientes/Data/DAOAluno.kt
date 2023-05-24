package com.example.cadastrodeclientes.Data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DAOAluno {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(aluno: Aluno)

    @Query("Select * from aluno")
    fun getAll():LiveData<List<Aluno>>


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(aluno: Aluno)


    //deleta todas
    @Query("DELETE from aluno")
    fun deleteAll()

    //deleta pelo id
    @Query("DELETE from aluno WHERE id = :id ")
    fun deleteById(id: Int)

}
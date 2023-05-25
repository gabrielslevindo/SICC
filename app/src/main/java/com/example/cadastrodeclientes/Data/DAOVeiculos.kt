package com.example.cadastrodeclientes.Data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DAOVeiculos {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(veiculo: Veiculo)

    @Query("Select * from veiculo")
    fun getAll():LiveData<List<Veiculo>>


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(veiculo: Veiculo)


    //deleta todas
    @Query("DELETE from veiculo")
    fun deleteAll()

    //deleta pelo id
    @Query("DELETE from veiculo WHERE id = :id ")
    fun deleteById(id: Int)
}
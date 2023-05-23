package com.example.cadastrodeclientes.Data

import androidx.room.*

@Dao
interface DAOFornecedores {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fornecedor: Fornecedor)

    @Query("Select * from fornecedor")
    fun getAll(): List<Fornecedor>


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(fornecedor: Fornecedor)


    //deleta todas
    @Query("DELETE from fornecedor")
    fun deleteAll()

    //deleta pelo id
    @Query("DELETE from fornecedor WHERE id = :id ")
    fun deleteById(id: Int)
}
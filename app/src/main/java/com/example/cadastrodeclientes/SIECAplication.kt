package com.example.cadastrodeclientes

import android.app.Application
import androidx.room.Room
import com.example.cadastrodeclientes.Data.AppDataBase

class SIECAplication: Application(){




    //Banco de dados e nome do banco;
    lateinit var  dataBase :AppDataBase


    override fun onCreate() {
        super.onCreate()
        dataBase= Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "SIEC-database"
        ).build()
    }



    fun getAppDataBase(): AppDataBase{

        return dataBase



    }




}
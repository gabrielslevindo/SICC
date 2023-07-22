package com.example.cadastrodeclientes.pressentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cadastrodeclientes.Data.DAOFornecedores
import com.example.cadastrodeclientes.Data.Fornecedor
import com.example.cadastrodeclientes.SIECAplication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FornecedorViewModel(private val FornecedorDao:DAOFornecedores,
private val dispacher: CoroutineDispatcher = Dispatchers.IO
                          ):ViewModel() {

    val FornecedorListLiveData: LiveData<List<Fornecedor>> = FornecedorDao.getAll()

    fun execute (fornecedorAction: FornecedorAction) {
        when (fornecedorAction.actiontype ) {
            ActionTypeFornecedor.DELETE.name -> deleteById(fornecedorAction.fornecedor!!.id)
            ActionTypeFornecedor.CREATE.name -> insertIntoDataBase(fornecedorAction.fornecedor!!)
            ActionTypeFornecedor.UPDATE.name -> updateIntoDataBase(fornecedorAction.fornecedor!!)
            ActionTypeFornecedor.DELETE_ALL.name -> deleteAll()
        }
    }



    private fun deleteAll() { // deletar todos

        viewModelScope.launch(dispacher) {


            FornecedorDao.deleteAll()
        }
    }
    private fun deleteById(id: Int) { // deletar todos

        viewModelScope.launch(dispacher) {

            FornecedorDao.deleteById(id)

        }

    }

    private fun insertIntoDataBase(fornecedor: Fornecedor) { // inserir na base de dados


        viewModelScope.launch(dispacher) {


           FornecedorDao.insert(fornecedor)

        }
    }

    private fun updateIntoDataBase(fornecedor: Fornecedor) { // fazer o update

        viewModelScope.launch(dispacher) {

            FornecedorDao.update(fornecedor)

        }
    }



    companion object {


        fun create(application: Application): FornecedorViewModel {

            val dataBaseInstance = (application as SIECAplication).getAppDataBase()
            val daofornecedor = dataBaseInstance.DAOFornecedores()
            return FornecedorViewModel(daofornecedor)


        }


    }



}
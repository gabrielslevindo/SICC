package com.example.cadastrodeclientes.pressentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cadastrodeclientes.Data.Aluno
import com.example.cadastrodeclientes.Data.DAOVeiculos
import com.example.cadastrodeclientes.Data.Veiculo
import com.example.cadastrodeclientes.SIECAplication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VeiculoViewModel(private val veiculoDao:DAOVeiculos,
 private val dispacher: CoroutineDispatcher = Dispatchers.IO
                       ):ViewModel() {


    val VeiculoListLiveData: LiveData<List<Veiculo>> = veiculoDao.getAll()



    fun execute (veiculosAction: VeiculosAction) {
        when (veiculosAction.actiontype ) {
           ActionTypeVeiculos.DELETE.name -> deleteById(veiculosAction.veiculos!!.id)
            ActionTypeVeiculos.CREATE.name -> insertIntoDataBase(veiculosAction.veiculos!!)
            ActionTypeVeiculos.UPDATE.name -> updateIntoDataBase(veiculosAction.veiculos!!)
            ActionTypeVeiculos.DELETE_ALL.name -> deleteAll()
        }
    }


    private fun deleteAll() { // deletar todos

        viewModelScope.launch(dispacher) {


            veiculoDao.deleteAll()
        }
    }
    private fun deleteById(id: Int) { // deletar todos

        viewModelScope.launch(dispacher) {

            veiculoDao.deleteById(id)

        }

    }

    private fun insertIntoDataBase(veiculo: Veiculo) { // inserir na base de dados


        viewModelScope.launch(dispacher) {


           veiculoDao.insert(veiculo)

        }
    }

    private fun updateIntoDataBase(veiculo: Veiculo) { // fazer o update

        viewModelScope.launch(dispacher) {

            veiculoDao.update(veiculo)

        }
    }


    companion object {


        fun create(application: Application): VeiculoViewModel {

            val dataBaseInstance = (application as SIECAplication).getAppDataBase()
            val daoveicu = dataBaseInstance.DAOVeiculos()
            return VeiculoViewModel(daoveicu)


        }


    }


}


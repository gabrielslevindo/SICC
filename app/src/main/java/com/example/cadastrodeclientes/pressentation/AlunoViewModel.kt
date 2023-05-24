package com.example.cadastrodeclientes.pressentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cadastrodeclientes.Data.Aluno
import com.example.cadastrodeclientes.Data.DAOAluno
import com.example.cadastrodeclientes.SIECAplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlunoViewModel (private val AlunoDao: DAOAluno) : ViewModel(){


    val AlunoListLiveData: LiveData<List<Aluno>> = AlunoDao.getAll()


 fun execute (alunoAction: AlunoAction) {
        when (alunoAction.actiontype ) {
            ActionTypeAluno.DELETE.name -> deleteById(alunoAction.aluno!!.id)
            ActionTypeAluno.CREATE.name -> insertIntoDataBase(alunoAction.aluno!!)
            ActionTypeAluno.UPDATE.name -> updateIntoDataBase(alunoAction.aluno!!)
            ActionTypeAluno.DELETE_ALL.name -> deleteAll()
        }
    }


    private fun deleteAll() { // deletar todos

        viewModelScope.launch(Dispatchers.IO) {


            AlunoDao.deleteAll()
        }
    }
    private fun deleteById(id: Int) { // deletar todos

        viewModelScope.launch(Dispatchers.IO) {

            AlunoDao.deleteById(id)

        }

    }

    private fun insertIntoDataBase(aluno: Aluno) { // inserir na base de dados


        viewModelScope.launch(Dispatchers.IO) {


            AlunoDao.insert(aluno)

        }
    }

    private fun updateIntoDataBase(aluno: Aluno) { // fazer o update

        viewModelScope.launch(Dispatchers.IO) {

            AlunoDao.update(aluno)

        }
    }


    companion object {


        fun create(application: Application): AlunoViewModel {

            val dataBaseInstance = (application as SIECAplication).getAppDataBase()
            val dao = dataBaseInstance.DAOAluno()
            return AlunoViewModel(dao)


        }


    }


}
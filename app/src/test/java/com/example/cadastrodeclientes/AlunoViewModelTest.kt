package com.example.cadastrodeclientes

import com.example.cadastrodeclientes.Data.Aluno
import com.example.cadastrodeclientes.Data.DAOAluno
import com.example.cadastrodeclientes.pressentation.ActionTypeAluno
import com.example.cadastrodeclientes.pressentation.AlunoAction
import com.example.cadastrodeclientes.pressentation.AlunoViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.concurrent.timerTask

class AlunoViewModelTest {



    private val alunoDao: DAOAluno = mock()


    private val underTest: AlunoViewModel by lazy {


        AlunoViewModel(alunoDao)


    }

    @Test
    fun delete_all()= runTest{


        //given

        val alunoAction = AlunoAction(aluno = null, actiontype = ActionTypeAluno.DELETE_ALL.name)

        //then
        underTest.execute(alunoAction)


        //when

        verify(alunoDao).deleteAll()



    }

    @Test
    fun updateAluno()= runTest{


        //given
        val aluno = Aluno(

            id = 1,
            name = "name",
            registration = "registration",
            telephone = "telephone",
            address = "address"
        )


        val alunoAction = AlunoAction(aluno = aluno, actiontype = ActionTypeAluno.UPDATE.name)

        //then
        underTest.execute(alunoAction)


        //when

        verify(alunoDao).update(aluno)



    }

    @Test
    fun DeleteAluno()= runTest{


        //given
        val aluno = Aluno(

            id = 1,
            name = "name",
            registration = "registration",
            telephone = "telephone",
            address = "address"
        )


        val alunoAction = AlunoAction(aluno = aluno, actiontype = ActionTypeAluno.DELETE.name)

        //then
        underTest.execute(alunoAction)


        //when

        verify(alunoDao).deleteById(aluno.id)

    }
    @Test
    fun InsertAluno()= runTest{


        //given
        val aluno = Aluno(

            id = 1,
            name = "name",
            registration = "registration",
            telephone = "telephone",
            address = "address"
        )


        val alunoAction = AlunoAction(aluno = aluno, actiontype = ActionTypeAluno.CREATE.name)

        //then
        underTest.execute(alunoAction)


        //when

        verify(alunoDao).insert(aluno)

    }
}


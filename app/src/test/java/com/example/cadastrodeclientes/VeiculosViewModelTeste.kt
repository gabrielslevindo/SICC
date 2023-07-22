package com.example.cadastrodeclientes

import com.example.cadastrodeclientes.Data.Aluno
import com.example.cadastrodeclientes.Data.DAOAluno
import com.example.cadastrodeclientes.Data.DAOVeiculos
import com.example.cadastrodeclientes.Data.Veiculo
import com.example.cadastrodeclientes.pressentation.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class VeiculosViewModelTeste {


    private val veiculoDao: DAOVeiculos = mock()


    private val underTest: VeiculoViewModel by lazy {


        VeiculoViewModel(veiculoDao)


    }

    @Test
    fun delete_all() = runTest {


        //given

        val veiculoAction =
            VeiculosAction(veiculos = null, actiontype = ActionTypeVeiculos.DELETE_ALL.name)

        //then
        underTest.execute(veiculoAction)


        //when

        verify(veiculoDao).deleteAll()


    }

    @Test
    fun updateAluno() = runTest {


        //given
        val veiculo = Veiculo(

            id = 1,
            brand = "brand",
            model = "model",
            year = "year",
            plate = "plate"
        )


        val veiculoAction =
            VeiculosAction(veiculos = veiculo, actiontype = ActionTypeVeiculos.UPDATE.name)

        //then
        underTest.execute(veiculoAction)


        //when

        verify(veiculoDao).update(veiculo)


    }

    @Test
    fun DeleteAluno() = runTest {


        //given
        val veiculo = Veiculo(

            id = 1,
            brand = "brand",
            model = "model",
            year = "year",
            plate = "plate"
        )


        val veiculoAction =
            VeiculosAction(veiculos = veiculo, actiontype = ActionTypeVeiculos.DELETE.name)

        //then
        underTest.execute(veiculoAction)


        //when

        verify(veiculoDao).deleteById(veiculo.id)

    }

    @Test
    fun InsertAluno() = runTest {


        //given
        val veiculo = Veiculo(

            id = 1,
            brand = "brand",
            model = "model",
            year = "year",
            plate = "plate"
        )


        val veiculoaction =
            VeiculosAction(veiculos = veiculo, actiontype = ActionTypeVeiculos.CREATE.name)

        //then
        underTest.execute(veiculoaction)


        //when

        verify(veiculoDao).insert(veiculo)

    }


}
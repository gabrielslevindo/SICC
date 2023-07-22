package com.example.cadastrodeclientes

import com.example.cadastrodeclientes.Data.DAOFornecedores
import com.example.cadastrodeclientes.Data.DAOVeiculos
import com.example.cadastrodeclientes.Data.Fornecedor
import com.example.cadastrodeclientes.Data.Veiculo
import com.example.cadastrodeclientes.pressentation.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class FornecedorViewModelTest {


    private val fornecedorDao: DAOFornecedores = mock()


    private val underTest: FornecedorViewModel by lazy {


        FornecedorViewModel(fornecedorDao)


    }

    @Test
    fun delete_all() = runTest {


        //given

        val fornecedorAction =
           FornecedorAction(fornecedor = null, actiontype = ActionTypeFornecedor.DELETE_ALL.name)

        //then
        underTest.execute(fornecedorAction)


        //when

        verify(fornecedorDao).deleteAll()


    }

    @Test
    fun updateAluno() = runTest {


        //given
        val fornecedor = Fornecedor(

            id = 1,
            name = "name",
           company = "commpany",
            telephone = "telephone",
            address = "address"
        )


        val fornecedorAction =
            FornecedorAction(fornecedor = fornecedor, actiontype = ActionTypeFornecedor.UPDATE.name)

        //then
        underTest.execute(fornecedorAction)


        //when

        verify(fornecedorDao).update(fornecedor)


    }

    @Test
    fun DeleteAluno() = runTest {


        //given
        val fornecedor = Fornecedor(

            id = 1,
            name = "name",
            company = "commpany",
            telephone = "telephone",
            address = "address"
        )


        val fornecedorAction =
           FornecedorAction(fornecedor = fornecedor, actiontype = ActionTypeFornecedor.DELETE.name)

        //then
        underTest.execute(fornecedorAction)


        //when

        verify(fornecedorDao).deleteById(fornecedor.id)

    }

    @Test
    fun InsertAluno() = runTest {


        //given
        val fornecedor = Fornecedor(

            id = 1,
            name = "name",
            company = "commpany",
            telephone = "telephone",
            address = "address"
        )


        val fornecedorAction =
            FornecedorAction(fornecedor= fornecedor, actiontype = ActionTypeFornecedor.CREATE.name)

        //then
        underTest.execute(fornecedorAction)


        //when

        verify(fornecedorDao).insert(fornecedor)

    }


}













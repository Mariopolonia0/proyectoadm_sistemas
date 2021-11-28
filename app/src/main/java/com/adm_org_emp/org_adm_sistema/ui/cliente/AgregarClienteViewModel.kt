package com.adm_org_emp.org_adm_sistema.ui.cliente

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Update
import com.adm_org_emp.org_adm_sistema.data.ClienteDb
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.adm_org_emp.org_adm_sistema.repository.ClienteRepository
import java.lang.IllegalArgumentException
import kotlinx.coroutines.launch


class AgregarClienteViewModel(app: Application) : ViewModel() {
    // TODO: Implement the ViewModel
    private val clienteRopository = ClienteRepository(ClienteDb.getInstance(app))
    // Aqui se envia una instacia de cliente nuevo al repositorio
    fun Insert (cliente:Cliente) = viewModelScope.launch {
        clienteRopository.insert(cliente)
    }
    fun Update (cliente:Cliente) = viewModelScope.launch {
        clienteRopository.update(cliente)
    }
    fun Delete (cliente:Cliente) = viewModelScope.launch {
        clienteRopository.delete(cliente)
    }


    class Factory (val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AgregarClienteViewModel::class.java)){
                @Suppress("UNCHECKER_CAST")
                return AgregarClienteViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
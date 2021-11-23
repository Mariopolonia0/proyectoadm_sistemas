package com.adm_org_emp.org_adm_sistema.ui.cliente

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.adm_org_emp.org_adm_sistema.data.ClienteDb
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.adm_org_emp.org_adm_sistema.repository.ClienteRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ClienteViewModel(app: Application) : ViewModel() {

    private val clienteRopository = ClienteRepository(ClienteDb.getInstance(app))

    //se declara una variable para asignarle la lista
    val listaCliente = clienteRopository.allCliente()

    // TODO: Implement the ViewModel
    class Factory (val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ClienteViewModel::class.java)){
                @Suppress("UNCHECKER_CAST")
                return ClienteViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
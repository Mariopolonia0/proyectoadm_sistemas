package com.adm_org_emp.org_adm_sistema.ui.local

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adm_org_emp.org_adm_sistema.data.ClienteDb
import com.adm_org_emp.org_adm_sistema.repository.ClienteRepository
import com.adm_org_emp.org_adm_sistema.ui.local.EditalLocalViewModel
import java.lang.IllegalArgumentException

class EditalLocalViewModel(app: Application) : ViewModel() {

    private val clienteRopository = ClienteRepository(ClienteDb.getInstance(app))

    //se declara una variable para asignarle la lista
    val listanombrespinner = clienteRopository.allCliente()
   // val listaapellidospinner = clienteRopository.allApellidoSpinner()

    // TODO: Implement the ViewModel

    class Factory (val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditalLocalViewModel::class.java)){
                @Suppress("UNCHECKER_CAST")
                return EditalLocalViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
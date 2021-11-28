package com.adm_org_emp.org_adm_sistema.ui.local

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adm_org_emp.org_adm_sistema.data.ClienteDb
import com.adm_org_emp.org_adm_sistema.data.LocalDb
import com.adm_org_emp.org_adm_sistema.repository.ClienteRepository
import com.adm_org_emp.org_adm_sistema.repository.LocalRepository
import com.adm_org_emp.org_adm_sistema.ui.cliente.ClienteViewModel
import java.lang.IllegalArgumentException

class LocalViewModel(app: Application) : ViewModel() {
    // TODO: Implement the ViewModel
    private val localRopository = LocalRepository(LocalDb.getInstace(app))

    //se declara una variable para asignarle la lista
    val listaLocal = localRopository.allLocal()

    class Factory (val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LocalViewModel::class.java)){
                @Suppress("UNCHECKER_CAST")
                return LocalViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
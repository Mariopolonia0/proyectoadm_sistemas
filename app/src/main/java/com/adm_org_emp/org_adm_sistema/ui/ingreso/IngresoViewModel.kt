package com.adm_org_emp.org_adm_sistema.ui.ingreso

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adm_org_emp.org_adm_sistema.data.IngresoDb
import com.adm_org_emp.org_adm_sistema.repository.IngresoRepository
import com.adm_org_emp.org_adm_sistema.ui.local.LocalViewModel
import java.lang.IllegalArgumentException

class IngresoViewModel(app: Application) : ViewModel() {
    // TODO: Implement the ViewModel
    private val ingresoRepository =  IngresoRepository(IngresoDb.getInstace(app))

    val listaIngreso = ingresoRepository.allIngreso()

    class Factory (val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(IngresoViewModel::class.java)){
                @Suppress("UNCHECKER_CAST")
                return IngresoViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
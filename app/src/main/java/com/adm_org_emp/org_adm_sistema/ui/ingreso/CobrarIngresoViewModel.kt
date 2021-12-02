package com.adm_org_emp.org_adm_sistema.ui.ingreso

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.adm_org_emp.org_adm_sistema.data.LocalDb
import com.adm_org_emp.org_adm_sistema.models.Ingreso
import com.adm_org_emp.org_adm_sistema.models.Local
import com.adm_org_emp.org_adm_sistema.repository.LocalRepository
import com.adm_org_emp.org_adm_sistema.ui.local.EditalLocalViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CobrarIngresoViewModel(app: Application) : ViewModel() {
    // TODO: Implement the ViewModel
    private val localRopository = LocalRepository(LocalDb.getInstace(app))

    //retorno los locales que tiene el cliente a el cual le boy a cobrar
    fun localcliente (clienteid :Int) :List<Local> {
       return localRopository.getclienteconlocal(clienteid)
    }



    class Factory (val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CobrarIngresoViewModel::class.java)){
                @Suppress("UNCHECKER_CAST")
                return CobrarIngresoViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
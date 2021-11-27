package com.adm_org_emp.org_adm_sistema.ui.local

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adm_org_emp.org_adm_sistema.ui.cliente.ClienteViewModel
import java.lang.IllegalArgumentException

class LocalViewModel(app: Application) : ViewModel() {
    // TODO: Implement the ViewModel

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
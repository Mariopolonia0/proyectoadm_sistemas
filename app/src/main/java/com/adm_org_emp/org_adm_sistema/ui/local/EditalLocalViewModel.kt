package com.adm_org_emp.org_adm_sistema.ui.local

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.adm_org_emp.org_adm_sistema.data.ClienteDb
import com.adm_org_emp.org_adm_sistema.data.IngresoDb
import com.adm_org_emp.org_adm_sistema.data.LocalDb
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.adm_org_emp.org_adm_sistema.models.Ingreso
import com.adm_org_emp.org_adm_sistema.models.Local
import com.adm_org_emp.org_adm_sistema.repository.ClienteRepository
import com.adm_org_emp.org_adm_sistema.repository.IngresoRepository
import com.adm_org_emp.org_adm_sistema.repository.LocalRepository
import com.adm_org_emp.org_adm_sistema.ui.local.EditalLocalViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class EditalLocalViewModel(app: Application) : ViewModel() {

    private val clienteRopository = ClienteRepository(ClienteDb.getInstance(app))
    private val localRopository = LocalRepository(LocalDb.getInstace(app))
    private val ingresoRopository = IngresoRepository(IngresoDb.getInstace(app))

    //se declara una variable para asignarle la lista
    val listaclientepinner = clienteRopository.getallClineteSpinner()

    fun Insert(local: Local)= viewModelScope.launch {
        localRopository.insert(local)
    }

    fun Update (local: Local) = viewModelScope.launch {
        localRopository.update(local)
    }

    fun InsetIngreso (ingreso:Ingreso) = viewModelScope.launch {
        ingresoRopository.insert(ingreso)
    }

    fun buscarIngresocliente (clienteId:Int) :List<Ingreso>{
       return ingresoRopository.buscarIngresocliente(clienteId)
    }

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
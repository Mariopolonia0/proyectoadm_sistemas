package com.adm_org_emp.org_adm_sistema.repository


import androidx.lifecycle.LiveData
import com.adm_org_emp.org_adm_sistema.data.IngresoDb
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.adm_org_emp.org_adm_sistema.models.Ingreso

class IngresoRepository(private val database :IngresoDb) {

    fun insert(ingreso: Ingreso){
        database.ingresoDao.insert(ingreso)
    }

    fun update (ingreso: Ingreso){
        database.ingresoDao.update(ingreso)
    }

    fun delete (ingreso: Ingreso){
        database.ingresoDao.delete(ingreso)
    }

    fun allIngreso(): LiveData<List<Ingreso>> {
        return database.ingresoDao.getAllIngreso()
    }
}
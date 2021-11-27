package com.adm_org_emp.org_adm_sistema.repository

import androidx.lifecycle.LiveData
import com.adm_org_emp.org_adm_sistema.data.LocalDb
import com.adm_org_emp.org_adm_sistema.models.Ingreso
import com.adm_org_emp.org_adm_sistema.models.Local

class LocalRepository(private val database : LocalDb) {

    fun insert(local:Local){
        database.localDao.insert(local)
    }

    fun update (local:Local){
        database.localDao.update(local)
    }

    fun delete (local:Local){
        database.localDao.delete(local)
    }

    fun allIngreso(): LiveData<List<Local>> {
        return database.localDao.getAllLocal()
    }

    fun getclienteconlocal(key : Long): List<Local> {
        return database.localDao.getclienteconlocal(key)
    }
}
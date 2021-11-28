package com.adm_org_emp.org_adm_sistema.repository

import androidx.lifecycle.LiveData
import com.adm_org_emp.org_adm_sistema.data.ClienteDb
import com.adm_org_emp.org_adm_sistema.models.Cliente

class ClienteRepository(private val database :ClienteDb) {

    fun insert(cliente: Cliente){
        database.clienteDao.insert(cliente)
    }

    fun update (cliente: Cliente){
        database.clienteDao.update(cliente)
    }

    fun delete (cliente: Cliente){
        database.clienteDao.delete(cliente)
    }

    fun allCliente(): LiveData<List<Cliente>> {
        return database.clienteDao.getAllCliente()
    }

    fun buscarCliente(long: Long): Cliente {
        return database.clienteDao.find(long)
    }
    fun getallClineteSpinner(): List<Cliente> {
        return database.clienteDao.getAllClienteSpinner()
    }
}
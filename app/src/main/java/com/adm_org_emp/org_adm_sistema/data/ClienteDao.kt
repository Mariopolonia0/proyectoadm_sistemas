package com.adm_org_emp.org_adm_sistema.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adm_org_emp.org_adm_sistema.models.Cliente


@Dao
interface ClienteDao {
    @Insert
    fun insert(cliente: Cliente)

    @Update
    fun update(cliente: Cliente)

    @Delete
    fun delete(cliente: Cliente)

    @Query("SELECT * FROM cliente WHERE ClienteId= :key")
    fun find(key :Long): Cliente

    @Query("SELECT * FROM cliente ORDER BY ClienteId ASC")
    fun getAllCliente(): LiveData<List<Cliente>>

    @Query("SELECT * FROM cliente ORDER BY ClienteId ASC")
    fun getAllClienteSpinner(): List<Cliente>
    //allClientespinner

}
package com.adm_org_emp.org_adm_sistema.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.adm_org_emp.org_adm_sistema.models.Ingreso

@Dao
interface IngresoDao {
    @Insert
    fun insert(ingreso: Ingreso)

    @Update
    fun update(ingreso: Ingreso)

    @Delete
    fun delete(ingreso: Ingreso)

    @Query("SELECT * FROM ingreso WHERE IngresoId= :key")
    fun find(key :Long): Ingreso

    @Query("SELECT * FROM ingreso ORDER BY IngresoId DESC")
    fun getAllIngreso(): LiveData<List<Ingreso>>
}
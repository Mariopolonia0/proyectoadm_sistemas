package com.adm_org_emp.org_adm_sistema.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adm_org_emp.org_adm_sistema.models.Ingreso

@Dao
interface IngresoDao {
    @Insert
    suspend fun insert(ingreso: Ingreso)

    @Update
    suspend fun update(ingreso: Ingreso)

    @Query("SELECT * FROM ingreso WHERE IngresoId= :key")
    suspend fun find(key :Long): Ingreso

    @Query("SELECT * FROM ingreso ORDER BY IngresoId DESC")
    fun getAllProducto(): LiveData<List<Ingreso>>
}
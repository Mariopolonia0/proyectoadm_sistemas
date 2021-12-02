package com.adm_org_emp.org_adm_sistema.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.adm_org_emp.org_adm_sistema.models.Ingreso
import com.adm_org_emp.org_adm_sistema.models.Local

@Dao
interface LocalDao {
    @Insert
    fun insert(local: Local)

    @Update
    fun update(local: Local)

    @Delete
    fun delete(local: Local)

    @Query("SELECT * FROM Local WHERE LocalId= :key")
    fun find(key :Long): Local

    @Query("SELECT * FROM Local ORDER BY LocalId DESC")
    fun getAllLocal(): LiveData<List<Local>>

    @Query("SELECT * FROM Local WHERE ClienteId = :key")
    fun getclienteconlocal(key:Int): List<Local>

}
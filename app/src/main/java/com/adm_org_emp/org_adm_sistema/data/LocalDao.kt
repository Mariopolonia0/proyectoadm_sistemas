package com.adm_org_emp.org_adm_sistema.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adm_org_emp.org_adm_sistema.models.Local

@Dao
interface LocalDao {
    @Insert
    suspend fun insert(local: Local)

    @Update
    suspend fun update(local: Local)

    @Query("SELECT * FROM local WHERE LocalId= :key")
    suspend fun find(key :Long): Local

    @Query("SELECT * FROM local ORDER BY LocalId DESC")
    fun getAllProducto(): LiveData<List<Local>>
}
package com.adm_org_emp.org_adm_sistema.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adm_org_emp.org_adm_sistema.models.Cliente

@Database(
    entities = [Cliente::class],
    version = 1,
    exportSchema = false
)

abstract class ClienteDb :RoomDatabase() {
    abstract val clienteDao : ClienteDao

    companion object{
        @Volatile
        private var INSTANCE : ClienteDb? = null

        fun getInstance (context: Context): ClienteDb{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ClienteDb::class.java,
                        "ClienteDb"
                    )
                        .allowMainThreadQueries().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
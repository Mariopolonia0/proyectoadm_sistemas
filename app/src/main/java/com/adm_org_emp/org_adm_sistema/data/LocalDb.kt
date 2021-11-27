package com.adm_org_emp.org_adm_sistema.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adm_org_emp.org_adm_sistema.models.Local

@Database(
    entities = [Local::class],
    version = 1,
    exportSchema = false
)

abstract class LocalDb :RoomDatabase() {
    abstract val localDao :LocalDao

    companion object{
        @Volatile
        private var INSTANCE : LocalDb? = null

        fun getInstace(context: Context): LocalDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDb::class.java,
                        "LocalDb"
                    )
                        .allowMainThreadQueries().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

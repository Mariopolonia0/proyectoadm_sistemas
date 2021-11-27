package com.adm_org_emp.org_adm_sistema.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adm_org_emp.org_adm_sistema.models.Ingreso

@Database(
    entities = [Ingreso::class],
    version = 1,
    exportSchema = false
)

abstract class IngresoDb : RoomDatabase() {
    abstract val ingresoDao:IngresoDao

    companion object{
        @Volatile
        private var INSTANCE : IngresoDb? = null

        fun getInstace(context: Context): IngresoDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        IngresoDb::class.java,
                        "IngresoDb"
                    )
                        .allowMainThreadQueries().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

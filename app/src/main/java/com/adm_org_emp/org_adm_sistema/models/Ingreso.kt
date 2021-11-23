package com.adm_org_emp.org_adm_sistema.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Ingreso(
    @PrimaryKey (autoGenerate = true)
    var IngresoId : Long,
    var ClienteId : Long,
    var FechaCumplida :Date,
    var FechaPagado :Date,
    var MontoTotal : Double
)

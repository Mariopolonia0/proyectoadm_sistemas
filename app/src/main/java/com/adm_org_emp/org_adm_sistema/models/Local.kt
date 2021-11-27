package com.adm_org_emp.org_adm_sistema.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Local(
    @PrimaryKey(autoGenerate = true)
    var LocalId : Long,
    var Nombre : String,
    var MontoRenta : Double,
    var TipoLocal : String,
    var FechaRegistro : String,
    var ClienteId : Long
)


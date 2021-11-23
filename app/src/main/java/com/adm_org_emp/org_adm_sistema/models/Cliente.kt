package com.adm_org_emp.org_adm_sistema.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cliente(
    @PrimaryKey (autoGenerate = true)
    var ClienteId : Long,
    var Nombre : String,
    var Apellido : String,
    var Dirrecion : String,
    var NumeroTelefono : String,
    var Referencia : String
)

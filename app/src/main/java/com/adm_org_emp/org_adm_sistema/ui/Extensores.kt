package com.adm_org_emp.org_adm_sistema.ui

import android.text.Editable
import android.view.View
import com.google.android.material.snackbar.Snackbar

    fun Editable?.getFloat():Float{
        return this.toString().toFloatOrNull()?:0f
    }

    fun Editable?.getDouble():Double{
        return this.toString().toDoubleOrNull()?:.0
    }

    fun View.showMessage(message:String) {
        Snackbar.make(this,message,Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()
    }

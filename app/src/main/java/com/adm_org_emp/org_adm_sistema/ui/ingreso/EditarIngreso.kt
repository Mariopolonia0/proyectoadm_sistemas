package com.adm_org_emp.org_adm_sistema.ui.ingreso

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adm_org_emp.org_adm_sistema.R

class EditarIngreso : Fragment() {

    companion object {
        fun newInstance() = EditarIngreso()
    }

    private lateinit var viewModel: EditarIngresoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.editar_ingreso_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditarIngresoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.adm_org_emp.org_adm_sistema.ui.ingreso

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adm_org_emp.org_adm_sistema.R

class IngresoFragment : Fragment() {

    companion object {
        fun newInstance() = IngresoFragment()
    }

    private lateinit var viewModel: IngresoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ingreso_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IngresoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
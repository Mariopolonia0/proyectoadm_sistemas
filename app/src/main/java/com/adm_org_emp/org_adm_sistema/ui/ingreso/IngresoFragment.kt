package com.adm_org_emp.org_adm_sistema.ui.ingreso

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adm_org_emp.org_adm_sistema.databinding.IngresoFragmentBinding

class IngresoFragment : Fragment() {

    private var _binding : IngresoFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: IngresoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = IngresoFragmentBinding.inflate(inflater,container,false)
        viewModel =
            ViewModelProvider(this,IngresoViewModel.Factory(requireActivity().application))
                .get(IngresoViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IngresoViewModel::class.java)
        // TODO: Use the ViewModel

        binding.listaIngresoRecyclerView.adapter = IngresoAdcter()
        val adater = binding.listaIngresoRecyclerView.adapter as IngresoAdcter

        viewModel.listaIngreso.observe(viewLifecycleOwner,{
            adater.submitList(it)
        })
    }

}
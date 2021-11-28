package com.adm_org_emp.org_adm_sistema.ui.local

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.databinding.LocalFragmentBinding

class LocalFragment : Fragment() {

    private var _binding :LocalFragmentBinding? = null
    private val binding get()  = _binding!!
    private lateinit var viewModel: LocalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LocalFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this,LocalViewModel.Factory(requireActivity().application))
                .get(LocalViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocalViewModel::class.java)
        // TODO: Use the ViewModel

        binding.agregarLocalFragmentfloatingActionButton.setOnClickListener(){
            findNavController().navigate(R.id.action_localFragment_to_editalLocalFragment)
        }

        binding.listaLocalRecyclerView.adapter = LocalAdacter()
        val adacter = binding.listaLocalRecyclerView.adapter as LocalAdacter

        viewModel.listaLocal.observe(viewLifecycleOwner,{
            adacter.submitList(it)
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
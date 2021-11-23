package com.adm_org_emp.org_adm_sistema.ui.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.databinding.ClienteFragmentBinding

class ClienteFragment : Fragment() {

    private var _binding : ClienteFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ClienteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = ClienteFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this,ClienteViewModel.Factory(requireActivity().application))
                .get(ClienteViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ClienteViewModel::class.java)
        // TODO: Use the ViewModel

        binding.agregarClienteFragmentfloatingActionButton.setOnClickListener(){
            findNavController().navigate(R.id.action_clienteFragment_to_agregarClienteFragment)
        }

        binding.listaRecyclerView.adapter = ClienteAdacter()
        val adacter = binding.listaRecyclerView.adapter as ClienteAdacter

        viewModel.listaCliente.observe(viewLifecycleOwner,{
            adacter.submitList(it)
        })

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
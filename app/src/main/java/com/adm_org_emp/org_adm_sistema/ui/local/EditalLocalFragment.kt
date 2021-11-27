package com.adm_org_emp.org_adm_sistema.ui.local

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.databinding.EditalLocalFragmentBinding


class EditalLocalFragment : Fragment(),AdapterView.OnItemSelectedListener{

    private var _binding :EditalLocalFragmentBinding? = null
    private val bindig get() = _binding!!

    private lateinit var viewModel: EditalLocalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EditalLocalFragmentBinding.inflate(inflater,container,false)
        viewModel =
            ViewModelProvider(this,EditalLocalViewModel.Factory(requireActivity().application))
                .get(EditalLocalViewModel::class.java)
        return bindig.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditalLocalViewModel::class.java)
        // TODO: Use the ViewModel

        llenarspiner()
        bindig.botonprobar.setOnClickListener(){
            bindig.NombreLocaltextInput.setText(bindig.spinnerclientevisa.selectedItemId.toString())
        }
    }


    fun llenarspiner(){
        viewModel.listanombrespinner.observe(viewLifecycleOwner,{
            val listcliente = ArrayAdapter<String>(requireContext(),R.layout.cliente_speener,R.id.clientespinnertextView)
            listcliente.add("0 - Sin Cliente")
            for (i in it.indices){
                listcliente.add(it.get(i).ClienteId.toString()+" - "+it.get(i).Nombre+" "+it.get(i).Apellido)
            }
            bindig.spinnerclientevisa.adapter = listcliente
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
        var hola:String = position.toString()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
    // override fun onItemSelected
}
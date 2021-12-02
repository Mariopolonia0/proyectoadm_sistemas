package com.adm_org_emp.org_adm_sistema.ui.ingreso

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.databinding.CobrarIngresoFragmentBinding
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.adm_org_emp.org_adm_sistema.models.Local

import java.util.*

class CobrarIngresoFragment : Fragment() {

    private var _binding : CobrarIngresoFragmentBinding? = null
    private val binding get()  = _binding!!

    private lateinit var viewModel: CobrarIngresoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CobrarIngresoFragmentBinding.inflate(inflater,container,false)
        viewModel =
            ViewModelProvider(this, CobrarIngresoViewModel.Factory(requireActivity().application))
                .get(CobrarIngresoViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CobrarIngresoViewModel::class.java)
        // TODO: Use the ViewModel
        binding.textViewClientecobro.setText(arguments?.getString("ClienteId"))
        binding.textViewfechacumplida.setText(arguments?.getString("FechaCumplida"))
        binding.textViewfechapagorealizado.setText(buscarfecha())

        binding.listalocalrecycleviewcobro.adapter = CobroAdater()
        val adater = binding.listalocalrecycleviewcobro.adapter as CobroAdater
        adater.submitList(viewModel.localcliente(binding.textViewClientecobro.text.toString().toInt()))


        binding.textViewmontototal.setText(calcularmonto())
        llenarspiner()
    }

    fun calcularmonto():String {

        val listalocal : List<Local> =viewModel.localcliente(binding.textViewClientecobro.text.toString().toInt())
        //val listalocal =viewModel.getlocal

        var total = 0.0
        for (i in listalocal.indices){
           Log.i("hola",requireArguments().getLong("ClienteId").toString())
            total = total + listalocal.get(i).MontoRenta
        }
        return total.toString()
    }

    fun llenarspiner(){
        val listcliente = ArrayAdapter<String>(requireContext(), R.layout.cliente_speener, R.id.clientespinnertextView)
        listcliente.addAll("EFECTIVO","TARJETA")
        binding.spinner.adapter = listcliente
    }


    fun buscarfecha (): String{
        val calendar = Calendar.getInstance()
       // calendar.set(calendar.get  .YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH)
        return String.format("%d/%d/%d",calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH) + 1 ,calendar.get(Calendar.YEAR))
    }

}
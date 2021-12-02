package com.adm_org_emp.org_adm_sistema.ui.ingreso

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.data.ClienteDb
import com.adm_org_emp.org_adm_sistema.databinding.VistaIngresoBinding
import com.adm_org_emp.org_adm_sistema.databinding.VistaLocaBinding
import com.adm_org_emp.org_adm_sistema.models.Ingreso
import com.adm_org_emp.org_adm_sistema.models.Local
import com.adm_org_emp.org_adm_sistema.repository.ClienteRepository

class IngresoAdcter(): RecyclerView.Adapter<IngresoAdcter.IngresoViewModel>() {



    private var ingresoList = emptyList<Ingreso>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngresoViewModel {
        val binding = VistaIngresoBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return IngresoViewModel(binding)
    }

    override fun onBindViewHolder(holder: IngresoViewModel, position: Int) {
        holder.bind(ingresoList[position])
    }

    override fun getItemCount(): Int {
        return ingresoList.size
    }

    fun submitList(list :List<Ingreso>){
        ingresoList = list
        notifyDataSetChanged()
    }

    inner class IngresoViewModel(private val binding: VistaIngresoBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(item: Ingreso) {

            val bundle = bundleOf(
                "ClienteId" to item.ClienteId.toString(),
                "FechaCumplida" to  item.FechaCumplida,
                "FechaPagado" to item.FechaPagado,
                "MontoTotal" to item.MontoTotal.toString()
            )

            binding.imageButtonCobro.setOnClickListener({
                binding.root.findNavController().navigate(R.id.action_ingresoFragment_to_cobrarIngresoFragment,bundle)
            })

            binding.ClientetextView.setText(item.ClienteId.toString())
            binding.fechacumplidatextView.setText(item.FechaCumplida)
            binding.montototaltextView.setText(item.MontoTotal.toString())
        }
    }
}


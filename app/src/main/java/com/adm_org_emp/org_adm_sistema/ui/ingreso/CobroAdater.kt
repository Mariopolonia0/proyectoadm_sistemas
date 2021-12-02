package com.adm_org_emp.org_adm_sistema.ui.ingreso

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adm_org_emp.org_adm_sistema.databinding.VistaCobroBinding
import com.adm_org_emp.org_adm_sistema.models.Local

class CobroAdater (): RecyclerView.Adapter<CobroAdater.CobroViewModel>() {

    private var LocalList = emptyList<Local>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CobroViewModel {
        val binding = VistaCobroBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CobroViewModel(binding)
    }

    override fun onBindViewHolder(holder: CobroViewModel, position: Int) {
        holder.bind(LocalList[position])
    }

    override fun getItemCount(): Int {
        return LocalList.size
    }

    fun submitList(list :List<Local>){
        LocalList = list
        notifyDataSetChanged()
    }

    inner class CobroViewModel(private val binding: VistaCobroBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(item: Local) {
            binding.NombreLocaltextView.setText(item.Nombre)
            binding.MontoLocaltextView.setText(item.MontoRenta.toString())
        }
    }
}

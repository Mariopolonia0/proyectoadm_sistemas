package com.adm_org_emp.org_adm_sistema.ui.local

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.data.ClienteDb
import com.adm_org_emp.org_adm_sistema.databinding.VistaLocaBinding
import com.adm_org_emp.org_adm_sistema.models.Local
import com.adm_org_emp.org_adm_sistema.repository.ClienteRepository


class LocalAdacter(): RecyclerView.Adapter<LocalAdacter.LocalViewHolder>()  {

    private var locaList = emptyList<Local>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val binding = VistaLocaBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return LocalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        holder.bind(locaList[position])
    }

    override fun getItemCount(): Int {
        return locaList.size
    }

    fun submitList(list :List<Local>){
        locaList = list
        notifyDataSetChanged()
    }

    inner class LocalViewHolder(private val binding: VistaLocaBinding) :
        RecyclerView.ViewHolder(binding.root){


        fun bind(item: Local){
            val bundle = bundleOf(
                "LocalId" to item.LocalId,
                "Nombre" to  item.Nombre,
                "MontoRenta" to item.MontoRenta.toString(),
                "TipoLocal" to item.TipoLocal,
                "FechaRegistro" to item.FechaRegistro,
                "ClienteId" to item.ClienteId.toString()
            )

            binding.imageButtonModicarLocal.setOnClickListener{
                binding.root.findNavController().navigate(R.id.action_localFragment_to_editalLocalFragment,bundle)
            }
            binding.NombretextViewlocal.text= item.Nombre
            binding.preciotextViewlocal.text = item.MontoRenta.toString()
            binding.tipotextViewlocal.text = item.TipoLocal
            if(item.ClienteId.toInt() > 0){
                if (ClienteRepository(ClienteDb.getInstance(itemView.context)).buscarCliente(item.ClienteId)== null){
                    binding.clientetextViewlocal.setText("No Tiene Cliente")
                }else {
                    binding.clientetextViewlocal.text =
                        ClienteRepository(ClienteDb.getInstance(itemView.context)).buscarCliente(
                            item.ClienteId
                        ).Nombre
                }
            }else
                    binding.clientetextViewlocal.setText("No Tiene Cliente")
        }
    }
}

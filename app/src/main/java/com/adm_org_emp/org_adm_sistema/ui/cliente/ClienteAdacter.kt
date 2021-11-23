package com.adm_org_emp.org_adm_sistema.ui.cliente

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.databinding.VistaClienteBinding
import com.adm_org_emp.org_adm_sistema.models.Cliente

class ClienteAdacter():RecyclerView.Adapter<ClienteAdacter.ClienteViewHolder>()  {

    private var clienteList = emptyList<Cliente>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val binding =VistaClienteBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ClienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        holder.bind(clienteList[position])
    }

    override fun getItemCount(): Int {
        return clienteList.size
    }

    fun submitList(list :List<Cliente>){
        clienteList = list
        notifyDataSetChanged()
    }

    inner class ClienteViewHolder(private val binding: VistaClienteBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(item:Cliente){
            val bundle = bundleOf(
                "ClienteId" to item.ClienteId,
                "Nombre" to  item.Nombre,
                "Apellido" to item.Apellido,
                "Dirrecion" to item.Dirrecion,
                "NumeroTelefono" to item.NumeroTelefono,
                "Referencia" to item.Referencia
            )

            binding.imageButton.setOnClickListener{
                binding.root.findNavController().navigate(R.id.agregarClienteFragment,bundle)

            }

            binding.imageButton2.setOnClickListener{
                binding.textcolor.setBackgroundColor(Color.parseColor("#FF0000"))
            }

            binding.mostranombreTextView.text = item.Nombre
            binding.mostrarapellidoTextView.text = item.Apellido
            binding.mostardireccionTextView.text = item.Dirrecion
            binding.mostrartelefonoTextView.text = item.NumeroTelefono
        }
    }
}



package com.adm_org_emp.org_adm_sistema.ui.cliente

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.data.ClienteDao
import com.adm_org_emp.org_adm_sistema.data.ClienteDb
import com.adm_org_emp.org_adm_sistema.data.LocalDb
import com.adm_org_emp.org_adm_sistema.databinding.VistaClienteBinding
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.adm_org_emp.org_adm_sistema.models.Local
import com.adm_org_emp.org_adm_sistema.repository.ClienteRepository
import com.adm_org_emp.org_adm_sistema.repository.LocalRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

            binding.imageButtonModicar.setOnClickListener{
                binding.root.findNavController().navigate(R.id.agregarClienteFragment,bundle)
            }

            binding.imageButtonDelete.setOnClickListener{

                val clienteRepositorio = ClienteRepository(ClienteDb.getInstance(it.context))
                clienteRepositorio.delete(Cliente(item.ClienteId,item.Nombre,item.Apellido,item.Dirrecion,item.NumeroTelefono,item.Referencia))

                MaterialAlertDialogBuilder(it.context)
                    .setTitle(R.string.titulo)
                    .setMessage(R.string.mensajeeliminar)
                    .show()

            }

            binding.mostranombreTextView.text = item.Nombre
            binding.mostrarapellidoTextView.text = item.Apellido
            binding.mostardireccionTextView.text = item.Dirrecion
            binding.mostrartelefonoTextView.text = item.NumeroTelefono
        }
    }
}



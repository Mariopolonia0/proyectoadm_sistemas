package com.adm_org_emp.org_adm_sistema.ui.cliente

import android.app.AlertDialog
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.databinding.AgregarClienteFragmentBinding
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.google.android.material.dialog.MaterialAlertDialogBuilder

//import android.support.v4.app.DialogFragment

class AgregarClienteFragment : Fragment() {

    private var _binding: AgregarClienteFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AgregarClienteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AgregarClienteFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this,AgregarClienteViewModel.Factory(requireActivity().application))
                .get(AgregarClienteViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,AgregarClienteViewModel.Factory(requireActivity().application))
            .get(AgregarClienteViewModel::class.java)
        // TODO: Use the ViewModel

        llenarcampos()

        binding.guardarfloatingActionButton.setOnClickListener{

            if (!Validar()){
            MaterialAlertDialogBuilder(it.context)
                .setTitle(resources.getString(R.string.titulo))
                .setMessage(resources.getString(R.string.mensajefaltacampo))
                .show()
            }else{
                //si el agumento esta nulo es que hay que agregar un cliente nuebo
                if( arguments?.getInt("ClienteId") != null){
                    viewModel.Update(ModificarCliente())
                    findNavController().navigateUp()
                    MaterialAlertDialogBuilder(it.context)
                        .setTitle(resources.getString(R.string.titulo))
                        .setMessage(resources.getString(R.string.mensajeguardar))
                        .show()
                }else{
                    //aqui se crea un cliente nuevo para enviarlo al viewmodel
                    viewModel.Insert(ClienteNuevo())
                    findNavController().navigateUp()
                    MaterialAlertDialogBuilder(it.context)
                        .setTitle(resources.getString(R.string.titulo))
                        .setMessage(resources.getString(R.string.mensajemodificar))
                        .show()
                }
            }
        }
    }

    fun ClienteNuevo(): Cliente{
        return Cliente(0,
            binding.NombreTextInputEditText.text.toString(),
            binding.ApellidoTextInputEditText.text.toString(),
            binding.DirrecionTextInputEditText.text.toString(),
            binding.NumeroTelefonoTextInputEditText.text.toString(),
            binding.ReferenciaTextInputEditText.text.toString()
        )
    }

    fun ModificarCliente(): Cliente{

        return Cliente(requireArguments().getLong("ClienteId"),
            binding.NombreTextInputEditText.text.toString(),
            binding.ApellidoTextInputEditText.text.toString(),
            binding.DirrecionTextInputEditText.text.toString(),
            binding.NumeroTelefonoTextInputEditText.text.toString(),
            binding.ReferenciaTextInputEditText.text.toString()
        )
    }

    fun Validar(): Boolean {
        var esValido = true;

        binding.NombreTextInputEditText.let {
            if(it.text.isNullOrEmpty()){
                it.error = "El Campo Nombre Esta Vacio"
                esValido = false
            }else
                it.error = null
        }

        binding.ApellidoTextInputEditText.let {
            if(it.text.isNullOrEmpty()){
                it.error = "El Campo Apellido Esta Vacio"
                esValido = false
            }else
                it.error = null
        }

        binding.DirrecionTextInputEditText.let {
            if(it.text.isNullOrEmpty()){
                it.error = "El Campo Dirrecion Esta Vacio "
                esValido = false
            }else
                it.error = null
        }

        binding.NumeroTelefonoTextInputEditText.let {
            if(it.text.isNullOrEmpty()){
                it.error = "El Campo Numero Telefono Esta Vacio"
                esValido = false
            }else
                it.error = null
        }
        binding.ReferenciaTextInputEditText.let {
            if(it.text.isNullOrEmpty()){
                it.error = "El Campo Referencia Esta Vacio"
                esValido = false
            }else
                it.error = null
        }
        return esValido
    }

    fun llenarcampos() {
        //aqui se resiven los dacto que vienen desde el adacter por medio de la
        // clase bundle y se usa el metodo arguments para recuperrarlo
        binding.NombreTextInputEditText.setText(arguments?.getString("Nombre"))
        binding.ApellidoTextInputEditText.setText(arguments?.getString("Apellido"))
        binding.DirrecionTextInputEditText.setText(arguments?.getString("Dirrecion"))
        binding.NumeroTelefonoTextInputEditText.setText(arguments?.getString("NumeroTelefono"))
        binding.ReferenciaTextInputEditText.setText(arguments?.getString("Referencia"))

    }

}
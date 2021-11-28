package com.adm_org_emp.org_adm_sistema.ui.local

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.databinding.EditalLocalFragmentBinding
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.adm_org_emp.org_adm_sistema.models.Local
import com.adm_org_emp.org_adm_sistema.ui.getDouble
import com.adm_org_emp.org_adm_sistema.ui.getFloat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*


class EditalLocalFragment : Fragment(){

    private var _binding :EditalLocalFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: EditalLocalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EditalLocalFragmentBinding.inflate(inflater,container,false)
        viewModel =
            ViewModelProvider(this,EditalLocalViewModel.Factory(requireActivity().application))
                .get(EditalLocalViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditalLocalViewModel::class.java)
        // TODO: Use the ViewModel

        llenarspiner()
        agregarfecha()
        llenarcampos()


        binding.floatingActionButtonAgregarLocal.setOnClickListener() {


            if (Validar() )
            {
                val a = MaterialAlertDialogBuilder(it.context)
                a.setTitle(R.string.titulo)
                a.setMessage(R.string.mensajeagregarclientelocal)
                a.setIcon(R.drawable.ic_klk)
                a.setPositiveButton("Aceptar"){dialog,which->



                            viewModel.Insert(llenarLocal())
                            findNavController().navigateUp()
                }
                a.setPositiveButtonIcon( getDrawable(it.context,R.drawable.ic_klk) )
                a.setNegativeButton("Cancelar"){
                        dialog,which ->
                }
                a.setCancelable(false)
                a.show()
            }else{

            }
        }
    }

    fun llenarcampos(){
        binding.NombreLocaltextInput.setText(arguments?.getString("Nombre"))
        binding.MontorentatextInputLocal.setText(arguments?.getString("MontoRenta"))
        binding.tipolocaltextInput.setText(arguments?.getString("TipoLocal"))
        binding.fecharegistrotextedit.setText(arguments?.getString("FechaRegistro"))
        binding.spinnerlistacliente.setSelection(arguments?.getString("ClienteId")!!.toInt())

    }

    fun llenarLocal():Local{
        return Local(
            0,
            binding.NombreLocaltextInput.text.toString(),
            binding.MontorentatextInputLocal.text.getDouble(),
            binding.tipolocaltextInput.text.toString(),
            binding.fecharegistrotextedit.text.toString(),
            binding.spinnerlistacliente.selectedItem.toString().split(" ").get(0).toLong()
        )
    }

    fun llenarspiner(){
            val listcliente = ArrayAdapter<String>(requireContext(),R.layout.cliente_speener,R.id.clientespinnertextView)
            listcliente.add("0 - Sin Cliente")
            val list : List<Cliente> = viewModel.listaclientepinner
            for (i in list.indices){
                listcliente.add(list.get(i).ClienteId.toString()+" - "+list.get(i).Nombre+" "+list.get(i).Apellido)
            }
            binding.spinnerlistacliente.adapter = listcliente
    }

    fun Validar(): Boolean {
        var esValido = true;

        binding.NombreLocaltextInput.let {
            if(it.text.isNullOrEmpty()){
                it.error = "Agregue El Nombre"
                esValido = false
            }else
                it.error = null
        }

        binding.MontorentatextInputLocal.let {
            if(it.text.getFloat() <= 0){
                it.error = "Agregue La Rente"
                esValido = false
            }else
                it.error = null
        }

        binding.tipolocaltextInput.let {
            if(it.text.isNullOrEmpty()){
                it.error = "Agregue El Tipo De Local "
                esValido = false
            }else
                it.error = null
        }

        binding.fecharegistrotextedit.let {
            if(it.text.isNullOrEmpty()){
                it.error = "Agregue Una Fecha"
                esValido = false
            }else
                it.error = null
        }

        return esValido
    }


    fun agregarfecha(){
        val mcurrentTime = Calendar.getInstance()
        val year = mcurrentTime.get(Calendar.YEAR)
        val month = mcurrentTime.get(Calendar.MONTH)
        val day = mcurrentTime.get(Calendar.DAY_OF_MONTH)

//        binding.fecharegistrotextedit.setOnFocusChangeListener{
//            binding,hasFocus ->
//            if (hasFocus){
//                //Toast.makeText(activity,"klk",Toast.LENGTH_LONG)
//            }
//
//
//        }


        binding.fecharegistrotextedit.setOnClickListener(){
            val datePicker = DatePickerDialog(it.context,android.R.style.ThemeOverlay_Material_Dialog, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    binding.fecharegistrotextedit.setText(String.format("%d / %d / %d", dayOfMonth, month + 1, year))
                }
            }, year, month, day);

            datePicker.show()
        }
    }


}



//binding.spinnerclientevisa.selectedItem.toString().split(" ").get(0)
// binding.MontorentatextInputLocal.text.toString().toDouble().toString()

//        binding.spinnerclientevisa.let {
//            if(it.selectedItemId.toInt() == 0)
//                esValido = false
//        }

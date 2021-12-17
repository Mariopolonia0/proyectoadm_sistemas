package com.adm_org_emp.org_adm_sistema.ui.local


import android.app.DatePickerDialog

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.navigation.fragment.findNavController
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.data.IngresoDb
import com.adm_org_emp.org_adm_sistema.databinding.EditalLocalFragmentBinding
import com.adm_org_emp.org_adm_sistema.models.Cliente
import com.adm_org_emp.org_adm_sistema.models.Ingreso
import com.adm_org_emp.org_adm_sistema.models.Local
import com.adm_org_emp.org_adm_sistema.repository.IngresoRepository
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
        if(arguments?.getString("Nombre") != null)
            llenarcampos()

        binding.floatingActionButtonAgregarLocal.setOnClickListener() {

            if (Validar())
            {
                if (binding.spinnerlistacliente.selectedItem.toString().split(" ").get(0).toInt() > 0){
                    val a = MaterialAlertDialogBuilder(it.context)
                    a.setTitle(R.string.titulo)
                    a.setMessage(R.string.DeseaGuardarElLocal)
                    a.setIcon(R.drawable.ic_info)
                    a.setPositiveButton(""){dialog,which->
                        guardarlocal()
                        findNavController().navigateUp()
                    }
                    a.setPositiveButtonIcon( getDrawable(it.context,R.drawable.ic_aceptar))
                    a.setNegativeButtonIcon(getDrawable(it.context,R.drawable.ic_cancelar))
                    a.setNegativeButton(""){dialog,which ->
                    }
                    a.setCancelable(false)
                    a.show()
                }else{
                    val a = MaterialAlertDialogBuilder(it.context)
                    a.setTitle(R.string.titulo)
                    a.setMessage(R.string.mensajeagregarclientelocal)
                    a.setIcon(R.drawable.ic_info)
                    a.setPositiveButton(""){dialog,which->
                        guardarlocal()
                        findNavController().navigateUp()
                    }
                    a.setPositiveButtonIcon( getDrawable(it.context,R.drawable.ic_aceptar))
                    a.setNegativeButtonIcon(getDrawable(it.context,R.drawable.ic_cancelar))
                    a.setNegativeButton(""){
                            dialog,which ->
                    }
                    a.setCancelable(false)
                    a.show()
                }
            }
        }
    }

    fun guardarlocal(){
        if(arguments?.getString("Nombre") == null){
            if (binding.spinnerlistacliente.selectedItem.toString().split(" ").get(0).toInt() > 0){
                viewModel.Insert(llenarLocal(0))
                if(viewModel.buscarIngresocliente(binding.spinnerlistacliente.selectedItem.toString().split(" ").get(0).toInt()).size == 0  )
                    viewModel.InsetIngreso(crearIngreso())

                findNavController().navigateUp()
            }else{
                viewModel.Insert(llenarLocal(0))
                findNavController().navigateUp()
            }

        }else{
            viewModel.Update(llenarLocal(requireArguments().getLong("LocalId")))
            findNavController().navigateUp()
        }
    }

    fun calcularfechacumplirfactura (): String{
        val calendar = Calendar.getInstance()
        calendar.set(
            binding.fecharegistrotextedit.text.toString().split("/").get(2).toInt(),
            binding.fecharegistrotextedit.text.toString().split("/").get(1).toInt(),
            binding.fecharegistrotextedit.text.toString().split("/").get(0).toInt()
        )
        calendar.add(Calendar.DAY_OF_YEAR,30)
        return String.format("%d/%d/%d",calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR))
    }

    fun crearIngreso():Ingreso{
        return Ingreso(0,
            binding.spinnerlistacliente.selectedItem.toString().split(" ").get(0).toLong(),
            calcularfechacumplirfactura(),
            "/0/0/0",
            0.0,
        )
    }

     fun llenarLocal(localId:Long):Local{
        return Local(
            localId,
            binding.NombreLocaltextInput.text.toString(),
            binding.MontorentatextInputLocal.text.getDouble(),
            binding.tipolocaltextInput.text.toString(),
            binding.fecharegistrotextedit.text.toString(),
            binding.spinnerlistacliente.selectedItem.toString().split(" ").get(0).toLong()
        )
    }

    fun llenarcampos(){
        binding.NombreLocaltextInput.setText(arguments?.getString("Nombre"))
        binding.MontorentatextInputLocal.setText(arguments?.getString("MontoRenta"))
        binding.tipolocaltextInput.setText(arguments?.getString("TipoLocal"))
        binding.fecharegistrotextedit.setText(arguments?.getString("FechaRegistro"))
        binding.spinnerlistacliente.setSelection(arguments?.getString("ClienteId")!!.toInt())
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

        binding.fecharegistrotextedit.setOnClickListener(){
            val datePicker = DatePickerDialog(it.context,android.R.style.ThemeOverlay_Material_Dialog, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    binding.fecharegistrotextedit.setText(String.format("%d/%d/%d", dayOfMonth, month + 1, year))
                }
            }, year, month, day);
            datePicker.show()
        }
    }
}



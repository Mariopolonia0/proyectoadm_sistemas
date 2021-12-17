package com.adm_org_emp.org_adm_sistema.ui.ingreso

import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.navigation.fragment.findNavController
import com.adm_org_emp.org_adm_sistema.R
import com.adm_org_emp.org_adm_sistema.databinding.CobrarIngresoFragmentBinding
import com.adm_org_emp.org_adm_sistema.models.Local
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.*
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import java.io.File
import java.util.*

class CobrarIngresoFragment : Fragment() {

    private var _binding : CobrarIngresoFragmentBinding? = null
    private val binding get()  = _binding!!

    private lateinit var viewModel: CobrarIngresoViewModel
    private val CODIGO_PERMISO_ALMACENAMIENTO = 3

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

        val cliente = viewModel.buscarcliente(binding.textViewClientecobro.text.toString().toLong())
        binding.textViewNombre.setText(cliente.Nombre+" "+cliente.Apellido)
        binding.listalocalrecycleviewcobro.adapter = CobroAdater()

        val adater = binding.listalocalrecycleviewcobro.adapter as CobroAdater
        adater.submitList(viewModel.localcliente(binding.textViewClientecobro.text.toString().toInt()))

        binding.textViewmontototal.setText(calcularmonto())
        llenarspiner()

        binding.floatingActionButtonImprimir.setOnClickListener(){
            //onRequestPermissionsResult()
            generarpdf(binding.textViewNombre.text.toString())
        }

//
    }
    fun generarpdf(cliente : String){

        val nombreArchivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +
                "/" +binding.textViewNombre.text.toString() +"_Factura.pdf"
        val file = File(nombreArchivo)
        if (!file.exists()) {
            file.createNewFile()
        }
        val pdfDocument = PdfDocument(PdfWriter(file))
        //pdfDocument.defaultPageSize = PageSize.A4
        val document = Document(pdfDocument)

        val texttitulo = Text("Cliente : ")
        texttitulo.setFontSize(20.0f)
        texttitulo.setFontColor(ColorConstants.BLACK)
        texttitulo.setTextAlignment(TextAlignment.LEFT)

        val text = Text(binding.textViewNombre.text.toString())
        text.setFontSize(20.0f)
        text.setFontColor(ColorConstants.RED)
        text.setTextAlignment(TextAlignment.LEFT)

        val textclientefactura = Paragraph(texttitulo)
        textclientefactura.add(text)
        document.add(textclientefactura)

        texttitulo.text = "Fecha Renta Cumplida  : "
        text.text = binding.textViewfechacumplida.text.toString()
        document.add(textclientefactura)

        texttitulo.text = "Fecha Cobro Realizada : "
        text.text = binding.textViewfechapagorealizado.text.toString()
        document.add(textclientefactura)

        texttitulo.text = "Tipo De PAgo : "
        text.text = binding.spinner.selectedItem.toString()
        document.add(textclientefactura)

        val table = Table(UnitValue.createPercentArray(floatArrayOf(8f, 10f, 10f, 25f, 10f))).useAllAvailableWidth()

        //Add Header Cells
        table.addHeaderCell(Cell().add(Paragraph("Fecha Registro").setTextAlignment(TextAlignment.CENTER)))
        table.addHeaderCell(Cell().add(Paragraph("Cod. Cliente").setTextAlignment(TextAlignment.CENTER)))
        table.addHeaderCell(Cell().add(Paragraph("Cod. Local").setTextAlignment(TextAlignment.CENTER)))
        table.addHeaderCell(Cell().add(Paragraph("Nombre").setTextAlignment(TextAlignment.CENTER)))
        table.addHeaderCell(Cell().add(Paragraph("Monto Renta").setTextAlignment(TextAlignment.CENTER)))
        val listalocal = viewModel.localcliente(binding.textViewClientecobro.text.toString().toInt())

        for (i in listalocal.indices){
            table.addCell(Cell().add(Paragraph(listalocal.get(i).FechaRegistro).setTextAlignment(TextAlignment.CENTER)))
            table.addCell(listalocal.get(i).ClienteId.toString()).setTextAlignment(TextAlignment.CENTER)
            table.addCell(Cell().add(Paragraph(listalocal.get(i).LocalId.toString()).setTextAlignment(TextAlignment.CENTER)))
            table.addCell(Cell().add(Paragraph(listalocal.get(i).Nombre).setTextAlignment(TextAlignment.LEFT)))
            table.addCell(Cell().add(Paragraph(listalocal.get(i).MontoRenta.toString()).setTextAlignment(TextAlignment.RIGHT)))

        }
        document.add(table)



        //val total= Paragraph(calcularmonto())
        val totaltitulo = Text("Monto Total : ")
        totaltitulo.setFontSize(20.0f)
        totaltitulo.setFontColor(ColorConstants.BLACK)
        totaltitulo.setTextAlignment(TextAlignment.LEFT)

        val total = Text(calcularmonto())
        total.setFontSize(20.0f)
        total.setFontColor(ColorConstants.RED)
        total.setTextAlignment(TextAlignment.LEFT)

        val paratotal = Paragraph(totaltitulo)
        paratotal.add(total)
        document.add(paratotal)

        //document.add(total)
        document.close()
        MaterialAlertDialogBuilder(binding.root.context)
            .setTitle(resources.getString(R.string.titulo))
            .setMessage("Se Imprimio el PDF")
            .setPositiveButtonIcon(getDrawable(binding.root.context,R.drawable.ic_aceptar))
            .setPositiveButton(""){dialog,which->
                findNavController().navigateUp()
            }
            .setCancelable(false)
            .show()

       // findNavController().navigateUp()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (grantResults.isEmpty()) {
            return
        }
        if (requestCode == CODIGO_PERMISO_ALMACENAMIENTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Aquí se ha concedido el permiso
            } else {
                MaterialAlertDialogBuilder(binding.root.context)
                    .setTitle(resources.getString(R.string.titulo))
                    .setMessage(resources.getString(R.string.mensajeguardar))
                    .show()
            }
        }
    }

    fun calcularmonto():String {

        val listalocal : List<Local> =viewModel.localcliente(binding.textViewClientecobro.text.toString().toInt())

        var total = 0.0
        for (i in listalocal.indices){
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
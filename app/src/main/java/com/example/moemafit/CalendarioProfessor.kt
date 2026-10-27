package com.example.moemafit

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarioProfessor : AppCompatActivity() {

    data class DiaItem(
        val diaMes: Int,
        val nomeDiaSemana: String,
        val dataCompletaStr: String,
        val temAulaColetiva: Boolean,
        val isDiaHoje: Boolean
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario_professor)

        val txtMesAno = findViewById<TextView>(R.id.txtMesAno)
        val listView = findViewById<ListView>(R.id.gridViewCalendario)

        val listaDias = mutableListOf<DiaItem>()
        val calendar = Calendar.getInstance()

        val formatoMesAno = SimpleDateFormat("MMMM yyyy", Locale("pt", "BR"))
        val formatoDiaSemana = SimpleDateFormat("EEEE", Locale("pt", "BR"))
        val formatoDataSimples = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        val formatoCompleto = SimpleDateFormat("dd 'de' MMMM", Locale("pt", "BR"))

        val dataHojeStr = formatoDataSimples.format(calendar.time)
        val mesAnoTexto = formatoMesAno.format(calendar.time)
        txtMesAno.text = mesAnoTexto.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale("pt", "BR")) else it.toString() }

        // Gerar 90 dias para a frente permitindo scroll vertical fluido
        for (i in 0..90) {
            val diaMes = calendar.get(Calendar.DAY_OF_MONTH)
            val nomeDiaSemana = formatoDiaSemana.format(calendar.time).replaceFirstChar { it.titlecase(Locale("pt", "BR")) }
            val dataCompletaStr = formatoCompleto.format(calendar.time)
            val dataAtualIteracao = formatoDataSimples.format(calendar.time)

            // A aula coletiva ocorre e aparece exclusivamente no dia atual
            val ehHoje = (dataAtualIteracao == dataHojeStr)
            val temAula = ehHoje

            listaDias.add(DiaItem(diaMes, nomeDiaSemana, dataCompletaStr, temAula, ehHoje))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        // Adaptador para preencher a lista rolável
        listView.adapter = object : BaseAdapter() {
            override fun getCount(): Int = listaDias.size
            override fun getItem(position: Int): Any = listaDias[position]
            override fun getItemId(position: Int): Long = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val view = convertView ?: layoutInflater.inflate(R.layout.item_dia_calendario, parent, false)
                val item = listaDias[position]

                val container = view.findViewById<LinearLayout>(R.id.layoutContainerDia)
                val txtSemana = view.findViewById<TextView>(R.id.txtDiaSemana)
                val txtNumero = view.findViewById<TextView>(R.id.txtNumeroDia)
                val imgHalter = view.findViewById<ImageView>(R.id.imgHalterAula)

                txtSemana.text = item.nomeDiaSemana
                txtNumero.text = item.dataCompletaStr

                if (item.isDiaHoje) {
                    container.setBackgroundResource(R.drawable.shape_circulo_branco)
                    txtSemana.setTextColor(Color.parseColor("#0A42E4")) // Azul Unifor
                    txtNumero.setTextColor(Color.parseColor("#0A42E4"))
                    imgHalter.visibility = View.VISIBLE
                    imgHalter.setColorFilter(Color.parseColor("#0A42E4"))
                } else {
                    container.background = null
                    txtSemana.setTextColor(Color.parseColor("#B0C4DE"))
                    txtNumero.setTextColor(Color.parseColor("#FFFFFF"))

                    if (item.temAulaColetiva) {
                        imgHalter.visibility = View.VISIBLE
                        imgHalter.setColorFilter(Color.parseColor("#FFFFFF"))
                    } else {
                        imgHalter.visibility = View.GONE
                    }
                }

                container.setOnClickListener {
                    Toast.makeText(applicationContext, "Selecionado: ${item.dataCompletaStr}", Toast.LENGTH_SHORT).show()
                }

                return view
            }
        }
    }
}
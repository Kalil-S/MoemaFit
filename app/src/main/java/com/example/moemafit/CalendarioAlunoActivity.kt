package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class CalendarioAlunoActivity : AppCompatActivity() {

    private lateinit var txtMesAno: TextView
    private lateinit var gridViewCalendario: GridView
    private lateinit var btnMesAnterior: ImageButton
    private lateinit var btnMesProximo: ImageButton
    private lateinit var btnPontos: Button

    private val calendar: Calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("MMMM yyyy", Locale("pt", "BR"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario_aluno)

        txtMesAno = findViewById(R.id.txtMesAno)
        gridViewCalendario = findViewById(R.id.gridViewCalendario)
        btnMesAnterior = findViewById(R.id.btnMesAnterior)
        btnMesProximo = findViewById(R.id.btnMesProximo)
        btnPontos = findViewById(R.id.btnPontos)

        // Clique no Logotipo da Unifor (Volta para a Home do Aluno)
        findViewById<View>(R.id.imgLogoUnifor)?.setOnClickListener {
            val intent = Intent(this, HomeAlunoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        // Clique no Menu Hambúrguer (Abre o Menu do Aluno)
        findViewById<View>(R.id.imgMenu)?.setOnClickListener {
            val intent = Intent(this, MenuAlunoActivity::class.java)
            startActivity(intent)
        }

        atualizarCalendario()

        btnMesAnterior.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            atualizarCalendario()
        }

        btnMesProximo.setOnClickListener {
            calendar.add(Calendar.MONTH, 1)
            atualizarCalendario()
        }

        // Botão Pontos -> Vai para a tela de pontos
        btnPontos.setOnClickListener {
            val intent = Intent(this, PontosAlunoActivity::class.java)
            startActivity(intent)
        }

        setupMenuInferior()
    }

    private fun atualizarCalendario() {
        val mesAnoStr = dateFormatter.format(calendar.time).replaceFirstChar { it.uppercase() }
        txtMesAno.text = mesAnoStr

        val diasDoMes = gerarDiasDoMes(calendar)

        // Adaptador usando exatamente os IDs do teu item_dia_calendario.xml
        val adapter = object : BaseAdapter() {
            override fun getCount(): Int = diasDoMes.size
            override fun getItem(position: Int): Any = diasDoMes[position]
            override fun getItemId(position: Int): Long = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val view = convertView ?: LayoutInflater.from(this@CalendarioAlunoActivity)
                    .inflate(R.layout.item_dia_calendario, parent, false)

                val layoutContainer = view.findViewById<LinearLayout>(R.id.layoutContainerDia)
                val txtDia = view.findViewById<TextView>(R.id.txtNumeroDia)
                val imgHaltere = view.findViewById<ImageView>(R.id.imgHalterAula)

                val diaStr = diasDoMes[position]
                if (diaStr.isEmpty()) {
                    txtDia.text = ""
                    layoutContainer.visibility = View.INVISIBLE
                    imgHaltere.visibility = View.GONE
                    layoutContainer.setOnClickListener(null)
                } else {
                    layoutContainer.visibility = View.VISIBLE
                    txtDia.text = diaStr

                    // Verificar se é o dia atual (ex: dia 28 no mês atual)
                    val hojeCal = Calendar.getInstance()
                    val isMesAnoAtual = calendar.get(Calendar.MONTH) == hojeCal.get(Calendar.MONTH) &&
                            calendar.get(Calendar.YEAR) == hojeCal.get(Calendar.YEAR)
                    val diaAtual = hojeCal.get(Calendar.DAY_OF_MONTH).toString()

                    if (isMesAnoAtual && diaStr == diaAtual) {
                        // Dia atual: Fundo branco redondo, texto azul e halter visível
                        layoutContainer.setBackgroundResource(R.drawable.shape_circulo_branco)
                        txtDia.setTextColor(android.graphics.Color.parseColor("#0A42E4"))
                        imgHaltere.visibility = View.VISIBLE
                        imgHaltere.setColorFilter(android.graphics.Color.parseColor("#0A42E4"))

                        // Clicar no dia atual / halter abre a tela de participar da aula coletiva
                        layoutContainer.setOnClickListener {
                            val intent = Intent(this@CalendarioAlunoActivity, ParticiparAulaColetivaActivity::class.java)
                            startActivity(intent)
                        }
                    } else {
                        // Dias normais do mês dentro do cartão azul
                        layoutContainer.setBackgroundColor(android.graphics.Color.TRANSPARENT)
                        txtDia.setTextColor(android.graphics.Color.parseColor("#FFFFFF"))
                        imgHaltere.visibility = View.GONE
                        layoutContainer.setOnClickListener(null)
                    }
                }
                return view
            }
        }

        gridViewCalendario.adapter = adapter
    }

    private fun gerarDiasDoMes(cal: Calendar): List<String> {
        val dias = mutableListOf<String>()
        val tempCal = cal.clone() as Calendar
        tempCal.set(Calendar.DAY_OF_MONTH, 1)

        val maxDias = tempCal.getActualMaximum(Calendar.DAY_OF_MONTH)
        val diaSemanaInicio = tempCal.get(Calendar.DAY_OF_WEEK) - 1

        for (i in 0 until diaSemanaInicio) {
            dias.add("")
        }

        for (i in 1..maxDias) {
            dias.add(i.toString())
        }
        return dias
    }

    private fun setupMenuInferior() {
        findViewById<LinearLayout>(R.id.menuAulaColetiva)?.setOnClickListener {
            val intent = Intent(this, ParticiparAulaColetivaActivity::class.java)
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.menuCriarFicha)?.setOnClickListener {
            Toast.makeText(this, "Minha Ficha", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.menuCadastrar)?.setOnClickListener {
            Toast.makeText(this, "Cadastrar", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.menuOpcoes)?.setOnClickListener {
            Toast.makeText(this, "Opções", Toast.LENGTH_SHORT).show()
        }
    }
}
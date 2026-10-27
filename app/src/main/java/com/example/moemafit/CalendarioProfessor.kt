package com.example.moemafit

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarioProfessor : AppCompatActivity() {

    private val calendarAtual = Calendar.getInstance()
    private val calendarioHoje = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario_professor)

        // ==========================================
        // MAPEAMENTO DOS ELEMENTOS DE TOPO
        // ==========================================
        val imgLogoUnifor = findViewById<ImageView>(R.id.imgLogoUnifor)
        val imgMenu = findViewById<ImageButton>(R.id.imgMenu)

        val txtMesAno = findViewById<TextView>(R.id.txtMesAno)
        val gridView = findViewById<GridView>(R.id.gridViewCalendario)
        val btnAnterior = findViewById<ImageButton>(R.id.btnMesAnterior)
        val btnProximo = findViewById<ImageButton>(R.id.btnMesProximo)

        // ==========================================
        // MAPEAMENTO DO MENU INFERIOR
        // ==========================================
        val menuAulaColetiva = findViewById<LinearLayout>(R.id.menuAulaColetiva)
        val menuCriarFicha = findViewById<LinearLayout>(R.id.menuCriarFicha)
        val menuCadastrar = findViewById<LinearLayout>(R.id.menuCadastrar)
        val menuOpcoes = findViewById<LinearLayout>(R.id.menuOpcoes)

        // ==========================================
        // CONFIGURAÇÃO DOS CLIQUES (NAVEGAÇÃO)
        // ==========================================

        // 1. Logo Unifor -> activity_home_prof.xml
        imgLogoUnifor.setOnClickListener {
            startActivity(Intent(this, HomeProf::class.java))
        }

        // 2. Menu Hambúrguer -> activity_menu.xml
        imgMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }

        // 3. Aula Coletiva (Menu) -> activity_criar_aula_coletiva.xml
        menuAulaColetiva.setOnClickListener {
            startActivity(Intent(this, CriarAulaColetivaActivity::class.java))
        }

        // 4. Criar Ficha -> activity_criar_ficha.xml
        menuCriarFicha.setOnClickListener {
            startActivity(Intent(this, CriarFicha::class.java))
        }

        // 5. Cadastrar -> activity_pre_cadastro.xml
        menuCadastrar.setOnClickListener {
            startActivity(Intent(this, PreCadastroAluno::class.java))
        }

        // 6. Opções -> activity_configuracoes.xml
        menuOpcoes.setOnClickListener {
            startActivity(Intent(this, ConfiguracoesActivity::class.java))
        }

        // ==========================================
        // LÓGICA DO CALENDÁRIO MENSAL
        // ==========================================
        fun atualizarCalendario() {
            val localePt = Locale("pt", "BR")
            val mesAnoFormat = SimpleDateFormat("MMMM yyyy", localePt)
            val textoMes = mesAnoFormat.format(calendarAtual.time)

            txtMesAno.text = textoMes.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(localePt) else it.toString()
            }

            val diasDoMes = mutableListOf<Int?>()
            val tempCal = calendarAtual.clone() as Calendar
            tempCal.set(Calendar.DAY_OF_MONTH, 1)

            val primeiroDiaSemana = tempCal.get(Calendar.DAY_OF_WEEK) - 1
            val maxDias = tempCal.getActualMaximum(Calendar.DAY_OF_MONTH)

            for (i in 0 until primeiroDiaSemana) {
                diasDoMes.add(null)
            }

            for (i in 1..maxDias) {
                diasDoMes.add(i)
            }

            gridView.adapter = object : BaseAdapter() {
                override fun getCount(): Int = diasDoMes.size
                override fun getItem(position: Int): Any? = diasDoMes[position]
                override fun getItemId(position: Int): Long = position.toLong()

                override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                    val context = parent?.context ?: applicationContext
                    val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_dia_calendario, parent, false)
                    val dia = diasDoMes[position]

                    val container = view.findViewById<LinearLayout>(R.id.layoutContainerDia)
                    val txtNumero = view.findViewById<TextView>(R.id.txtNumeroDia)
                    val imgHalter = view.findViewById<ImageView>(R.id.imgHalterAula)

                    if (dia == null) {
                        txtNumero?.text = ""
                        imgHalter?.visibility = View.GONE
                        container?.background = null
                        container?.isEnabled = false
                    } else {
                        txtNumero?.text = dia.toString()
                        container?.isEnabled = true

                        val ehHoje = (dia == calendarioHoje.get(Calendar.DAY_OF_MONTH) &&
                                calendarAtual.get(Calendar.MONTH) == calendarioHoje.get(Calendar.MONTH) &&
                                calendarAtual.get(Calendar.YEAR) == calendarioHoje.get(Calendar.YEAR))

                        if (ehHoje) {
                            container?.setBackgroundResource(R.drawable.shape_circulo_branco)
                            txtNumero?.setTextColor(Color.parseColor("#0A42E4"))
                            imgHalter?.visibility = View.VISIBLE
                            imgHalter?.setColorFilter(Color.parseColor("#0A42E4"))

                            // Clique específico no dia atual (com aula coletiva/halter)
                            container?.setOnClickListener {
                                val intent = Intent(context, DetalhesAulaColetiva::class.java)
                                context.startActivity(intent)
                            }
                        } else {
                            container?.background = null
                            txtNumero?.setTextColor(Color.parseColor("#FFFFFF"))
                            imgHalter?.visibility = View.GONE

                            // Clique normal para os outros dias do mês
                            container?.setOnClickListener {
                                Toast.makeText(context, "Dia $dia selecionado", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    return view
                }
            }
        }

        btnAnterior.setOnClickListener {
            calendarAtual.add(Calendar.MONTH, -1)
            atualizarCalendario()
        }

        btnProximo.setOnClickListener {
            calendarAtual.add(Calendar.MONTH, 1)
            atualizarCalendario()
        }

        atualizarCalendario()
    }
}
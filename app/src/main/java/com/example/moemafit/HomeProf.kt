package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeProf : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_prof)

        // Botão do menu superior (hambúrguer)
        val btnMenu = findViewById<ImageButton>(R.id.btn_menu)
        btnMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        // NOVO: Botão do calendário completo no topo do card azul
        val btnCalendarioFull = findViewById<ImageButton>(R.id.btn_calendario_full)
        btnCalendarioFull.setOnClickListener {
            val intent = Intent(this, CalendarioProfessor::class.java)
            startActivity(intent)
        }

        // Dia com aula coletiva (dia 18) no card
        val btnAulaColetiva = findViewById<LinearLayout>(R.id.btn_aula_coletiva)
        btnAulaColetiva.setOnClickListener {
            val intent = Intent(this, DetalhesAulaColetiva::class.java)
            startActivity(intent)
        }

        // Menu inferior: Aula coletiva
        val btnCriarAulaColetiva = findViewById<LinearLayout>(R.id.btn_nav_aula_coletiva)
        btnCriarAulaColetiva.setOnClickListener {
            val intent = Intent(this, CriarAulaColetivaActivity::class.java)
            startActivity(intent)
        }

        // Ver mais feedbacks
        val txtVejaMais = findViewById<TextView>(R.id.text_veja_mais)
        txtVejaMais.setOnClickListener {
            val intent = Intent(this, ListaFeedbacksActivity::class.java)
            startActivity(intent)
        }

        // Menu inferior: Criar ficha
        val btnCriarFicha = findViewById<LinearLayout>(R.id.btn_criarFicha)
        btnCriarFicha.setOnClickListener {
            val intent = Intent(this, CriarFicha::class.java)
            startActivity(intent)
        }

        // Menu inferior: Cadastrar
        val btnCadastrar = findViewById<LinearLayout>(R.id.btn_cadastrar)
        btnCadastrar.setOnClickListener {
            val intent = Intent(this, PreCadastroAluno::class.java)
            startActivity(intent)
        }

        // Menu inferior: Buscar
        val btnNavBuscar = findViewById<LinearLayout>(R.id.btn_nav_buscar)
        btnNavBuscar.setOnClickListener {
            val intent = Intent(this, BuscarAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
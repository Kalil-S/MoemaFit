package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton // Importe o ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeProf : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_prof)

        val btnMenu = findViewById<ImageButton>(R.id.btn_menu)
        btnMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        val btnAulaColetiva = findViewById<LinearLayout>(R.id.btn_aula_coletiva)
        btnAulaColetiva.setOnClickListener {
            val intent = Intent(this, DetalhesAulaColetiva::class.java)
            startActivity(intent)
        }

        val btnCriarAulaColetiva = findViewById<LinearLayout>(R.id.btn_nav_aula_coletiva)
        btnCriarAulaColetiva.setOnClickListener {
            val intent = Intent(this, CriarAulaColetivaActivity::class.java)
            startActivity(intent)
        }

        val txtVejaMais = findViewById<TextView>(R.id.text_veja_mais)
        txtVejaMais.setOnClickListener {
            val intent = Intent(this, ListaFeedbacksActivity::class.java)
            startActivity(intent)
        }

        val btnCriarFicha = findViewById<LinearLayout>(R.id.btn_criarFicha)
        btnCriarFicha.setOnClickListener {
            val intent = Intent(this, CriarFicha::class.java)
            startActivity(intent)
        }

        val btnCadastrar = findViewById<LinearLayout>(R.id.btn_cadastrar)
        btnCadastrar.setOnClickListener {
            val intent = Intent(this, PreCadastroAluno::class.java)
            startActivity(intent)
        }
        val btnNavBuscar = findViewById<LinearLayout>(R.id.btn_nav_buscar)
        btnNavBuscar.setOnClickListener {
            val intent = Intent(this, BuscarAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
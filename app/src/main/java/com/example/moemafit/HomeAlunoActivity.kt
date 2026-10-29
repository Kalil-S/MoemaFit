package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeAlunoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_aluno)

        // Botão do calendário no card azul da home do aluno
        val btnCalendario = findViewById<ImageButton>(R.id.btn_calendario_full)

        btnCalendario?.setOnClickListener {
            val intent = Intent(this, CalendarioAlunoActivity::class.java)
            startActivity(intent)
        }

        // Clique no Menu Hambúrguer (Abre o Menu do Aluno)
        findViewById<View>(R.id.btn_menu)?.setOnClickListener {
            val intent = Intent(this, MenuAlunoActivity::class.java)
            startActivity(intent)
        }

        // Botão de Aulas Coletivas (lá embaixo na tela)
        findViewById<View>(R.id.btn_nav_aula_coletiva)?.setOnClickListener {
            val intent = Intent(this, ParticiparAulaColetivaActivity::class.java)
            startActivity(intent)
        }

        // --- NOVO: Botão "Minha Ficha" (na barra inferior) ---
        findViewById<View>(R.id.btn_minha_ficha)?.setOnClickListener {
            val intent = Intent(this, MinhaFichaAlunoActivity::class.java)
            startActivity(intent)
        }

        // Botão de Opções / Configurações (na barra inferior)
        findViewById<View>(R.id.btn_nav_opcoes)?.setOnClickListener {
            val intent = Intent(this, ConfiguracoesAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
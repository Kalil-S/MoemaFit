package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class CalendarioAlunoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario_aluno) // Substitui pelo nome exato do teu XML se for diferente

        // --- 1. Clique no Menu Hambúrguer (Topo) ---
        // Suporta tanto "imgMenu" (presente no XML do calendário) quanto "btn_menu"
        val abrirMenu = findViewById<View>(R.id.imgMenu) ?: findViewById(R.id.imgMenu)
        abrirMenu?.setOnClickListener {
            val intent = Intent(this, MenuAlunoActivity::class.java)
            startActivity(intent)
        }

        // --- 2. Barra Inferior: Botão de Aulas Coletivas ---
        findViewById<View>(R.id.btn_nav_aula_coletiva)?.setOnClickListener {
            val intent = Intent(this, ParticiparAulaColetivaActivity::class.java)
            startActivity(intent)
        }

        // --- 3. Barra Inferior: Botão "Minha Ficha" ---
        findViewById<View>(R.id.btn_minha_ficha)?.setOnClickListener {
            val intent = Intent(this, MinhaFichaAlunoActivity::class.java)
            startActivity(intent)
        }

        // --- 4. Barra Inferior: Botão de Opções / Configurações ---
        findViewById<View>(R.id.btn_nav_opcoes)?.setOnClickListener {
            val intent = Intent(this, ConfiguracoesAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
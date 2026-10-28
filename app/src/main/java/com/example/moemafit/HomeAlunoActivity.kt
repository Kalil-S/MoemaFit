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

        btnCalendario.setOnClickListener {
            val intent = Intent(this, CalendarioAlunoActivity::class.java)
            startActivity(intent)
        }

        // --- NOVO: Clique no Menu Hambúrguer (Abre o Menu do Aluno) ---
        findViewById<View>(R.id.btn_menu)?.setOnClickListener {
            val intent = Intent(this, MenuAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PontosAlunoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pontos_aluno)

        // Botão voltar tradicional da tela de pontos (caso exista no layout)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        btnVoltar?.setOnClickListener {
            finish() // Fecha a tela de pontos e retorna ao calendário
        }

        // --- NOVO: Clique no Logotipo da Unifor (Volta para a Home do Aluno) ---
        findViewById<View>(R.id.imgLogoUnifor)?.setOnClickListener {
            val intent = Intent(this, HomeAlunoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        // --- NOVO: Clique no Menu Hambúrguer (Abre o Menu do Aluno) ---
        findViewById<View>(R.id.imgMenu)?.setOnClickListener {
            val intent = Intent(this, MenuAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
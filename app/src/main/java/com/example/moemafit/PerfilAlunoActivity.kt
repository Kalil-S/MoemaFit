package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class PerfilAlunoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_aluno)

        // Botão de voltar (seta superior esquerda)
        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_perfil)
        btnVoltar?.setOnClickListener {
            finish()
        }

        // Clique no cartão da ficha ativa
        val cardFicha = findViewById<MaterialCardView>(R.id.cardFichaPerfil)
        cardFicha?.setOnClickListener {
            val intent = Intent(this, MinhaFichaAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.moemafit

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ParticiparAulaColetivaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participar_aula_coletiva)

        // Botão de voltar (seta superior esquerda)
        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_detalhes_coletiva)

        btnVoltar.setOnClickListener {
            finish() // Fecha esta activity atual e retorna automaticamente ao calendário
        }
    }
}
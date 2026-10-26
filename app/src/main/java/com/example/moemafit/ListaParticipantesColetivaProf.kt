package com.example.moemafit

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ListaParticipantesAulaColetiva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_participantes_coletiva)

        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_lista_participantes)
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
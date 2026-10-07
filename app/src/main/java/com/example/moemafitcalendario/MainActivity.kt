package com.example.moemafitcalendario

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Mapear os elementos clicáveis
        val logoUnifor = findViewById<ImageButton>(R.id.imageButton3)
        val menuHamburger = findViewById<ImageButton>(R.id.imageButton)
        val btnPontos = findViewById<Button>(R.id.button)
        val halter1 = findViewById<ImageButton>(R.id.imageButton4)
        val halter2 = findViewById<ImageButton>(R.id.imageButton5)
        val calendario = findViewById<ImageButton>(R.id.imageButton7)

        // 2. Definir as ações de clique
        logoUnifor.setOnClickListener {
            Toast.makeText(this, "Logo Unifor clicado", Toast.LENGTH_SHORT).show()
        }

        menuHamburger.setOnClickListener {
            Toast.makeText(this, "Menu clicado", Toast.LENGTH_SHORT).show()
        }

        btnPontos.setOnClickListener {
            Toast.makeText(this, "Botão Pontos clicado", Toast.LENGTH_SHORT).show()
        }

        halter1.setOnClickListener {
            Toast.makeText(this, "Primeiro halter clicado", Toast.LENGTH_SHORT).show()
        }

        halter2.setOnClickListener {
            Toast.makeText(this, "Segundo halter clicado", Toast.LENGTH_SHORT).show()
        }

        calendario.setOnClickListener {
            Toast.makeText(this, "Calendário clicado", Toast.LENGTH_SHORT).show()
        }
    }
}
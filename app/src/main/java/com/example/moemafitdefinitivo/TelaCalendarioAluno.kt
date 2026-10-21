package com.example.moemafitdefinitivo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TelaCalendarioAluno : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telacalendarioaluno)

        // 1. Botão da Logo da Unifor no topo
        findViewById<ImageButton>(R.id.imgLogoUnifor)?.setOnClickListener {
            Toast.makeText(this, "Logo da Unifor clicada!", Toast.LENGTH_SHORT).show()
        }

        // 2. Botão do Menu Hambúrguer
        findViewById<ImageButton>(R.id.imgMenu)?.setOnClickListener {
            Toast.makeText(this, "Menu clicado!", Toast.LENGTH_SHORT).show()
        }

        // 3. Botão de Calendário no topo do card azul
        findViewById<ImageButton>(R.id.btnCalendarioTopo)?.setOnClickListener {
            Toast.makeText(this, "Calendário superior clicado!", Toast.LENGTH_SHORT).show()
        }

        // 4. Botão do Dia 18 (Halter)
        findViewById<LinearLayout>(R.id.btnDia18)?.setOnClickListener {
            Toast.makeText(this, "Treino do dia 18 clicado!", Toast.LENGTH_SHORT).show()
        }

        // 5. Botão do Dia 29 (Halter)
        findViewById<LinearLayout>(R.id.btnDia29)?.setOnClickListener {
            Toast.makeText(this, "Treino do dia 29 clicado!", Toast.LENGTH_SHORT).show()
        }

        // 6. Botão inferior "Pontos"
        findViewById<Button>(R.id.btnPontos)?.setOnClickListener {
            Toast.makeText(this, "Botão Pontos clicado!", Toast.LENGTH_SHORT).show()
        }
    }
}
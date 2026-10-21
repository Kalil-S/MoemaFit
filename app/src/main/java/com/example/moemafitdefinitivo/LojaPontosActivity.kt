package com.example.moemafitdefinitivo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LojaPontosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loja_pontos)

        // Logo e Menu
        findViewById<ImageButton>(R.id.imgLogoUnifor)?.setOnClickListener {
            Toast.makeText(this, "Logo da Unifor clicada!", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageButton>(R.id.imgMenu)?.setOnClickListener {
            Toast.makeText(this, "Menu clicado!", Toast.LENGTH_SHORT).show()
        }

        // Compras
        findViewById<Button>(R.id.btnComprarStreak)?.setOnClickListener {
            Toast.makeText(this, "Streak congelado com sucesso!", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnComprarPocao)?.setOnClickListener {
            Toast.makeText(this, "Poção 2x adquirida!", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnComprarBau)?.setOnClickListener {
            Toast.makeText(this, "Baú misterioso aberto!", Toast.LENGTH_SHORT).show()
        }

        // Botão Voltar
        findViewById<Button>(R.id.btnVoltar)?.setOnClickListener {
            finish() // Fecha a loja e retorna à tela anterior (calendário)
        }
    }
}
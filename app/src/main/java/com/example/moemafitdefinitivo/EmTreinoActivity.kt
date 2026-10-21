package com.example.moemafitdefinitivo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmTreinoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_em_treino)

        // Logo e Menu
        findViewById<ImageButton>(R.id.imgLogoUnifor)?.setOnClickListener {
            Toast.makeText(this, "Unifor MoemaFit", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageButton>(R.id.imgMenu)?.setOnClickListener {
            Toast.makeText(this, "Menu de Opções", Toast.LENGTH_SHORT).show()
        }

        // Botão Problemas
        findViewById<Button>(R.id.btnProblemas)?.setOnClickListener {
            Toast.makeText(this, "Relate o problema ao instrutor.", Toast.LENGTH_LONG).show()
        }

        // Botão Finalizar
        findViewById<Button>(R.id.btnFinalizarTreino)?.setOnClickListener {
            Toast.makeText(this, "Treino finalizado com sucesso! Parabéns!", Toast.LENGTH_SHORT).show()
            finish() // Retorna à tela anterior
        }
    }
}
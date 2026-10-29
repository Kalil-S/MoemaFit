package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MinhaFichaAlunoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minha_ficha)

        // Botão de voltar (seta superior esquerda) -> Retorna para a Home do Aluno
        findViewById<ImageButton>(R.id.btnVoltarFicha)?.setOnClickListener {
            val intent = Intent(this, HomeAlunoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        // Botão Iniciar Treino A
        findViewById<MaterialButton>(R.id.btnIniciarTreinoA)?.setOnClickListener {
            val intent = Intent(this, EmTreinoAlunoActivity::class.java)
            intent.putExtra("TREINO_SELECIONADO", "A")
            startActivity(intent)
        }

        // Botão Iniciar Treino B
        findViewById<MaterialButton>(R.id.btnIniciarTreinoB)?.setOnClickListener {
            val intent = Intent(this, EmTreinoAlunoActivity::class.java)
            intent.putExtra("TREINO_SELECIONADO", "B")
            startActivity(intent)
        }

        // Botão Iniciar Treino C
        findViewById<MaterialButton>(R.id.btnIniciarTreinoC)?.setOnClickListener {
            val intent = Intent(this, EmTreinoAlunoActivity::class.java)
            intent.putExtra("TREINO_SELECIONADO", "C")
            startActivity(intent)
        }
    }
}
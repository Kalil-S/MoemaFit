package com.example.moemafit

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class FeedbackAlunoActivity : AppCompatActivity() {

    private var currentRating = 0
    private lateinit var stars: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_aluno)

        // Botão de voltar (seta superior) -> Fecha a tela e volta ao Menu Aluno
        findViewById<ImageButton>(R.id.btnVoltarFeedback).setOnClickListener {
            finish()
        }

        val etFeedback = findViewById<EditText>(R.id.etFeedback)
        val btnEnviar = findViewById<MaterialButton>(R.id.btnEnviarFeedback)

        // Mapear as estrelas do layout
        stars = listOf(
            findViewById(R.id.star1),
            findViewById(R.id.star2),
            findViewById(R.id.star3),
            findViewById(R.id.star4),
            findViewById(R.id.star5)
        )

        // Configurar cliques nas estrelas para preenchimento dinâmico
        for (i in stars.indices) {
            stars[i].setOnClickListener {
                currentRating = i + 1
                updateStars(currentRating)
            }
        }

        // Ação do botão enviar
        btnEnviar.setOnClickListener {
            val texto = etFeedback.text.toString().trim()
            if (currentRating == 0) {
                Toast.makeText(this, "Por favor, selecione uma avaliação em estrelas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Feedback enviado com sucesso!", Toast.LENGTH_SHORT).show()
            finish() // Retorna ao menu após enviar com sucesso
        }
    }

    // Função para alternar os ícones das estrelas (azul preenchida vs vazia)
    private fun updateStars(rating: Int) {
        for (i in stars.indices) {
            if (i < rating) {
                // Estrela preenchida (azul por dentro)
                stars[i].setImageResource(R.drawable.ic_star_filled)
            } else {
                // Estrela vazia
                stars[i].setImageResource(R.drawable.ic_star_border)
            }
        }
    }
}
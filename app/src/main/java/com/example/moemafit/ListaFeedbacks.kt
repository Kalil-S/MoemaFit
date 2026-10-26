package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ListaFeedbacksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_prof)

        val btnFechar = findViewById<ImageButton>(R.id.btn_fechar)
        btnFechar.setOnClickListener {
            finish()
        }

        // 2. Clique no Primeiro Feedback (Esteira danificada)
        val feedbackItem1 = findViewById<LinearLayout>(R.id.feedback_item_1)
        feedbackItem1.setOnClickListener {
            // Abre a tela de Detalhes do Feedback
            val intent = Intent(this, DetalhesFeedbackActivity::class.java)
            startActivity(intent)
        }
    }
}
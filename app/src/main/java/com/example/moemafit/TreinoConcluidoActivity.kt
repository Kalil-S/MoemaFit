package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class TreinoConcluidoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treino_concluido)

        // Botão Fechar -> Retorna diretamente para a Home do Aluno
        findViewById<MaterialButton>(R.id.btnFecharConcluido)?.setOnClickListener {
            val intent = Intent(this, HomeAlunoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        // Botão Avalie-nos -> Vai para a tela de Feedback
        findViewById<MaterialButton>(R.id.btnAvalieNos)?.setOnClickListener {
            val intent = Intent(this, FeedbackAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
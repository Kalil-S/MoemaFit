package com.example.moemafit

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class PerfilAlunoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_aluno)

        // Botão de voltar (seta superior esquerda)
        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_perfil)
        btnVoltar?.setOnClickListener {
            finish()
        }
    }
}
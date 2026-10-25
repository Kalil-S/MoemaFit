package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PerfilProfessorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_professor)

        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_detalhes_coletiva)
        btnVoltar.setOnClickListener {
            finish()
        }

        val btnAlterarFoto = findViewById<FrameLayout>(R.id.btn_alterar_foto)
        btnAlterarFoto.setOnClickListener {
            Toast.makeText(this, "Funcionalidade de trocar foto em breve!", Toast.LENGTH_SHORT).show()
        }

        val btnHistoricoFichas = findViewById<LinearLayout>(R.id.btn_historico_fichas)
        btnHistoricoFichas.setOnClickListener {
            Toast.makeText(this, "Abrir histórico de fichas", Toast.LENGTH_SHORT).show()
        }

        val btnRedefinirSenha = findViewById<LinearLayout>(R.id.btn_redefinir_senha)
        btnRedefinirSenha.setOnClickListener {
            val intent = Intent(this, RedefinirSenhaActivity::class.java)
            startActivity(intent)
        }
    }
}
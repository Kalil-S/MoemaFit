
package com.example.moemafitdefinitivo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PerfilAlunoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_aluno)

        // Logo e Menu
        findViewById<ImageButton>(R.id.imgLogoUnifor)?.setOnClickListener {
            Toast.makeText(this, "Unifor MoemaFit", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageButton>(R.id.imgMenu)?.setOnClickListener {
            Toast.makeText(this, "Menu acionado", Toast.LENGTH_SHORT).show()
        }

        // Ações de Botões
        findViewById<Button>(R.id.btnHistoricoFichas)?.setOnClickListener {
            Toast.makeText(this, "Abrindo Histórico de Fichas...", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnRedefinirSenha)?.setOnClickListener {
            Toast.makeText(this, "Solicitação de redefinição de senha enviada!", Toast.LENGTH_SHORT).show()
        }

        // Botão Voltar
        findViewById<Button>(R.id.btnVoltarPerfil)?.setOnClickListener {
            finish()
        }
    }
}
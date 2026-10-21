package com.example.moemafitdefinitivo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CriarAulaColetivaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_aula_coletiva)

        // Logo e Menu
        findViewById<ImageButton>(R.id.imgLogoUnifor)?.setOnClickListener {
            Toast.makeText(this, "Unifor MoemaFit", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageButton>(R.id.imgMenu)?.setOnClickListener {
            Toast.makeText(this, "Menu de Opções", Toast.LENGTH_SHORT).show()
        }

        // Botão Salvar
        findViewById<Button>(R.id.btnSalvarAula)?.setOnClickListener {
            val nomeAula = findViewById<EditText>(R.id.editNomeAula)?.text.toString()
            if (nomeAula.isNotEmpty()) {
                Toast.makeText(this, "Aula '$nomeAula' criada com sucesso!", Toast.LENGTH_SHORT).show()
                finish() // Retorna à tela anterior
            } else {
                Toast.makeText(this, "Por favor, preencha o nome da aula.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.example.moemafitperfil

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSeta = findViewById<ImageButton?>(R.id.imageButton)
        val btnHistorico = findViewById<Button?>(R.id.button)
        val btnRedefinirSenha = findViewById<Button?>(R.id.button2)

        btnSeta?.setOnClickListener {
            Toast.makeText(this, "Seta clicada!", Toast.LENGTH_SHORT).show()
        }

        btnHistorico?.setOnClickListener {
            Toast.makeText(this, "Histórico clicado!", Toast.LENGTH_SHORT).show()
        }

        btnRedefinirSenha?.setOnClickListener {
            Toast.makeText(this, "Redefinir Senha clicado!", Toast.LENGTH_SHORT).show()
        }
    }
}
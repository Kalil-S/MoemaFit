package com.example.moemafit

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PrimeiroLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primeiro_login)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarCadas)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalCadas)
        btnVoltar.setOnClickListener {
            finish()
        }
        btnFinalizar.setOnClickListener {
            Toast.makeText(this, "Cadastro finalizado com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
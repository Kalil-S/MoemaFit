package com.example.moemafitdefinitivo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Aguarda 2.5 segundos e avança para a tela principal
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, TelaCalendarioAluno::class.java)
            startActivity(intent)
            finish() // Fecha a splash para não voltar nela ao carregar "Voltar"
        }, 2500)
    }
}
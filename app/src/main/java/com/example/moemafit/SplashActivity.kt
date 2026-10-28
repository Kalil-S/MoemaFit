package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // O XML com a imagem da avestruz

        // Espera 2 segundos e vai para a MainActivity (Login)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Fecha a splash para o utilizador não voltar para ela ao carregar no "voltar"
        }, 2000)
    }
}
package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton // Importe o ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class HomeProf : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_prof)

        val btnMenu = findViewById<ImageButton>(R.id.btn_menu)
        btnMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        val btnAulaColetiva = findViewById<LinearLayout>(R.id.btn_aula_coletiva)
        btnAulaColetiva.setOnClickListener {
            val intent = Intent(this, DetalhesAulaActivity::class.java)
            startActivity(intent)
        }

        val btnCriarFicha = findViewById<LinearLayout>(R.id.btn_criarFicha)
        btnCriarFicha.setOnClickListener {
            val intent = Intent(this, CriarFicha::class.java)
            startActivity(intent)
        }
    }
}
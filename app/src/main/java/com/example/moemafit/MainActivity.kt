package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEntrar = findViewById<Button>(R.id.button_entrar_login)
        val inputSenha = findViewById<TextInputEditText>(R.id.editTextTextPassword)

        val txtPrimeiroLogin = findViewById<TextView>(R.id.textView)

        btnEntrar.setOnClickListener {
            val senhaDigitada = inputSenha.text.toString().trim()

            when (senhaDigitada) {
                "1" -> {
                    // Vai para a tela do Aluno
                    val intent = Intent(this, HomeAlunoActivity::class.java)
                    startActivity(intent)
                }
                "0" -> {
                    // Vai para a tela do Professor
                    val intent = Intent(this, HomeProf::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "Digite '1' (Aluno) ou '0' (Professor) na senha", Toast.LENGTH_SHORT).show()
                }
            }
        }

        txtPrimeiroLogin.setOnClickListener {
            val intent = Intent(this, PrimeiroLoginActivity::class.java)
            startActivity(intent)
        }
    }
}
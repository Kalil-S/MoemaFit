package com.example.moemafit

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AlunoAchadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aluno_achado)

        findViewById<View>(R.id.btnVoltar).setOnClickListener { finish() }

        val nome = intent.getStringExtra("nome") ?: "Carolina Cavalcante"
        val email = intent.getStringExtra("email") ?: "Carol@edu.unifor.br"
        val objetivo = intent.getStringExtra("objetivo") ?: "Hipertrofia"
        val peso = intent.getStringExtra("peso") ?: "56,80"
        val validade = intent.getStringExtra("validade") ?: "2 meses"

        findViewById<TextView>(R.id.txtNome).text = nome
        findViewById<TextView>(R.id.txtEmail).text = "E-mail: $email"
        findViewById<TextView>(R.id.txtObjetivo).text = "Objetivo: $objetivo"
        findViewById<TextView>(R.id.txtPeso).text = "Peso: $peso"
        findViewById<TextView>(R.id.txtValidade).text = "Validade ficha: $validade"

        // Histórico de exemplo (será substituído pelos dados reais numa etapa futura)
        findViewById<TextView>(R.id.txtNomeFicha).text = "Ficha ${nome.substringBefore(" ")}"
        findViewById<TextView>(R.id.txtDataFicha).text = "Data: 07/08/2026"
    }
}

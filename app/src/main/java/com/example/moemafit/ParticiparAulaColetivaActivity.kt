package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ParticiparAulaColetivaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participar_aula_coletiva)

        // 1. Botão da seta superior esquerda -> Retorna para a Home do Aluno
        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_detalhes_coletiva)
        btnVoltar?.setOnClickListener {
            val intent = Intent(this, HomeAlunoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        // 2. Botão de Participar -> Redireciona para o Calendário do Aluno
        // (Certifica-te de que o ID no XML do botão de participar é btnParticipar ou ajusta conforme o teu layout)
        val btnParticipar = findViewById<MaterialButton>(R.id.btnParticipar) // Substitui pelo ID exato do teu botão de participar no XML, caso seja diferente
        btnParticipar?.setOnClickListener {
            val intent = Intent(this, CalendarioAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
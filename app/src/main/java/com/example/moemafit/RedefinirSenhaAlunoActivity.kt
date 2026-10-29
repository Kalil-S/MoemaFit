package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RedefinirSenhaAlunoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redefinir_senha_aluno)

        // Botão de voltar (seta superior) -> Retorna para o Menu Aluno
        findViewById<View>(R.id.btnVoltar).setOnClickListener {
            // Podes usar finish() ou forçar o Intent para o MenuAlunoActivity
            val intent = Intent(this, MenuAlunoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        // Botão de salvar a nova senha
        findViewById<View>(R.id.btnSalvar).setOnClickListener {
            // Aqui entraria a chamada ao backend/Firebase numa etapa futura
            Toast.makeText(this, "Senha redefinida com sucesso!", Toast.LENGTH_SHORT).show()

            // Retorna ao menu após salvar
            val intent = Intent(this, MenuAlunoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}
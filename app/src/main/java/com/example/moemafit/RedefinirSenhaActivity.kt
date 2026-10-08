package com.example.moemafit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RedefinirSenhaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redefinir_senha)

        findViewById<View>(R.id.btnVoltar).setOnClickListener { finish() }

        findViewById<View>(R.id.btnSalvar).setOnClickListener {
            // Aqui entraria a chamada ao backend/Firebase numa etapa futura
            Toast.makeText(this, "Senha salva", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

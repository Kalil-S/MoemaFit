package com.example.moemafit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class ConfiguracoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)

        findViewById<View>(R.id.btnVoltar).setOnClickListener { finish() }

        findViewById<View>(R.id.txtRedefinirSenha).setOnClickListener {
            startActivity(Intent(this, RedefinirSenhaActivity::class.java))
        }

        val prefs = getSharedPreferences("config", Context.MODE_PRIVATE)
        val swFeedback = findViewById<SwitchCompat>(R.id.switchFeedbacks)
        val swCalendario = findViewById<SwitchCompat>(R.id.switchCalendario)

        swFeedback.isChecked = prefs.getBoolean("desativar_feedbacks", false)
        swCalendario.isChecked = prefs.getBoolean("desativar_calendario", false)

        swFeedback.setOnCheckedChangeListener { _, marcado ->
            prefs.edit().putBoolean("desativar_feedbacks", marcado).apply()
        }
        swCalendario.setOnCheckedChangeListener { _, marcado ->
            prefs.edit().putBoolean("desativar_calendario", marcado).apply()
        }
    }
}

package com.example.moemafit

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<View>(R.id.btnVoltar).setOnClickListener { finish() }

        findViewById<View>(R.id.itemPerfil).setOnClickListener {
            Toast.makeText(this, "Tela de perfil ainda não implementada", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.itemConfig).setOnClickListener {
            startActivity(Intent(this, ConfiguracoesActivity::class.java))
        }
        findViewById<View>(R.id.itemSair).setOnClickListener {
            mostrarPopupSair()
        }
    }

    private fun mostrarPopupSair() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_confirmar_sair)

        dialog.findViewById<View>(R.id.btnSim).setOnClickListener {
            dialog.dismiss()
            finishAffinity() // futuramente: limpar sessão e ir para o login
        }
        dialog.findViewById<View>(R.id.btnNao).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent) // deixa só os cantos arredondados do card
            setLayout(
                (resources.displayMetrics.widthPixels * 0.85).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
}

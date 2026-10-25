package com.example.moemafit

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnVoltar = findViewById<ImageButton>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }

        val itemPerfil = findViewById<LinearLayout>(R.id.itemPerfil)
        itemPerfil.setOnClickListener {
            val intent = Intent(this, PerfilProfessorActivity::class.java)
            startActivity(intent)
        }

        val itemConfig = findViewById<LinearLayout>(R.id.itemConfig)
        itemConfig.setOnClickListener {
            val intent = Intent(this, ConfiguracoesActivity::class.java)
            startActivity(intent)
        }

        val itemSair = findViewById<LinearLayout>(R.id.itemSair)
        itemSair.setOnClickListener {
            abrirDialogSair()
        }
    }

    private fun abrirDialogSair() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_confirmar_sair)

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val btnConfirmarSair = dialog.findViewById<Button>(R.id.btnSimSairConta)
        val btnCancelarSair = dialog.findViewById<Button>(R.id.btnNaoSairConta)

        btnConfirmarSair?.setOnClickListener {
            dialog.dismiss()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        btnCancelarSair?.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
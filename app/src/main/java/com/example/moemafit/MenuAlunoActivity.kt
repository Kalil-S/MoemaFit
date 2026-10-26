package com.example.moemafit

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuAlunoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_aluno)

        findViewById<View>(R.id.btnVoltar).setOnClickListener { finish() }

        findViewById<View>(R.id.itemPerfil).setOnClickListener { avisoEmBreve() }
        findViewById<View>(R.id.itemConfig).setOnClickListener { avisoEmBreve() }
        findViewById<View>(R.id.itemFeedback).setOnClickListener { avisoEmBreve() }

        findViewById<View>(R.id.itemAulaColetiva).setOnClickListener {
            startActivity(Intent(this, DetalhesAulaColetiva::class.java))
        }
        findViewById<View>(R.id.itemSair).setOnClickListener { mostrarPopupSair() }
    }

    private fun avisoEmBreve() {
        Toast.makeText(this, "Tela ainda não implementada", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarPopupSair() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_confirmar_sair)

        dialog.findViewById<View>(R.id.btnSimSairConta).setOnClickListener {
            dialog.dismiss()
            finishAffinity()
        }
        dialog.findViewById<View>(R.id.btnNaoSairConta).setOnClickListener { dialog.dismiss() }

        dialog.show()
        dialog.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            setLayout((resources.displayMetrics.widthPixels * 0.85).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
}

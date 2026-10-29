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

        // Redireciona para a tela de Perfil do Aluno
        findViewById<View>(R.id.itemPerfil).setOnClickListener {
            val intent = Intent(this, PerfilAlunoActivity::class.java)
            startActivity(intent)
        }

        // Redireciona para a tela de Configurações do Aluno
        findViewById<View>(R.id.itemConfig).setOnClickListener {
            val intent = Intent(this, ConfiguracoesAlunoActivity::class.java)
            startActivity(intent)
        }

        // Redireciona para a tela de Feedback do Aluno
        findViewById<View>(R.id.itemFeedback).setOnClickListener {
            val intent = Intent(this, FeedbackAlunoActivity::class.java)
            startActivity(intent)
        }

        // Redireciona para o Calendário do Aluno (Aulas Coletivas)
        findViewById<View>(R.id.itemAulaColetiva).setOnClickListener {
            val intent = Intent(this, CalendarioAlunoActivity::class.java)
            startActivity(intent)
        }

        // Botão Sair - Abre o pop-up customizado do aluno
        findViewById<View>(R.id.itemSair).setOnClickListener { mostrarPopupSair() }
    }

    private fun mostrarPopupSair() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        // Aponta para o XML exato do pop-up de sair do aluno
        dialog.setContentView(R.layout.dialog_confirmar_sair_aluno)

        // Botão "Sim" -> Limpa o histórico, fecha as telas e volta para o MainActivity (Login)
        dialog.findViewById<View>(R.id.btnSimSairConta)?.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Botão "Não" -> Apenas fecha o pop-up e permanece no menu do aluno
        dialog.findViewById<View>(R.id.btnNaoSairConta)?.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            setLayout((resources.displayMetrics.widthPixels * 0.85).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
}
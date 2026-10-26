package com.example.moemafit

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class DetalhesFeedbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_feedback_prof)
        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_detalhes_coletiva)
        btnVoltar.setOnClickListener {
            finish()
        }
        val cardAnexo = findViewById<MaterialCardView>(R.id.card_anexo_imagem)
        cardAnexo.setOnClickListener {
            abrirImagemExpandida()
        }
    }
    private fun abrirImagemExpandida() {
        val dialog = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.dialog_imagem_expandida)

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val btnFechar = dialog.findViewById<ImageButton>(R.id.btn_fechar_imagem)
        btnFechar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
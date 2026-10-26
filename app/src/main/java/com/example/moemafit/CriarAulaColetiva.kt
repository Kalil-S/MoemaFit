package com.example.moemafit

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class CriarAulaColetivaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_aula_coletiva)

        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar)
        btnVoltar.setOnClickListener {
            finish()
        }

        val btnSalvar = findViewById<MaterialButton>(R.id.btnSalvar)
        btnSalvar.setOnClickListener {
            abrirDialogSucesso()
        }
    }

    private fun abrirDialogSucesso() {
        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_sucesso_criacao_aula_coletiva)

        // Configurações para deixar o fundo transparente e tamanho correto
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // Impede que o usuário feche o dialog clicando fora dele (opcional, mas recomendado)
        dialog.setCancelable(false)

        // Encontra o botão "OK" dentro do Dialog
        val btnOk = dialog.findViewById<MaterialButton>(R.id.btnOkSucesso)

        btnOk?.setOnClickListener {
            dialog.dismiss() // Fecha o Dialog
            finish()         // Fecha a tela de Criar Aula e volta para a Home
        }

        dialog.show()
    }
}
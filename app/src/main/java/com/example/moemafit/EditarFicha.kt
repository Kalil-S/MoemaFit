package com.example.moemafit

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class EditarFichaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_ficha)

        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_detalhes_coletiva)
        btnVoltar.setOnClickListener {
            finish()
        }

        val btnCancelar = findViewById<MaterialButton>(R.id.btn_cancelar)
        btnCancelar.setOnClickListener {
            finish()
        }

        val btnSalvar = findViewById<MaterialButton>(R.id.btn_salvar_pre_cadastro)
        btnSalvar.setOnClickListener {
            abrirDialogSalvar()
        }

        val acaoEditarTreino = {
            Toast.makeText(this, "Abrir edição do exercício", Toast.LENGTH_SHORT).show()
        }

        val cardTreinoA = findViewById<MaterialCardView>(R.id.card_editar_treino_a)
        val cardTreinoB = findViewById<MaterialCardView>(R.id.card_editar_treino_b)
        val cardTreinoC = findViewById<MaterialCardView>(R.id.card_editar_treino_c)

        cardTreinoA.setOnClickListener { acaoEditarTreino() }
        cardTreinoB.setOnClickListener { acaoEditarTreino() }
        cardTreinoC.setOnClickListener { acaoEditarTreino() }
    }

    private fun abrirDialogSalvar() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_confirmacao_salvar_edicao_ficha)

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val btnNao = dialog.findViewById<MaterialButton>(R.id.btn_dialog_nao)
        val btnSim = dialog.findViewById<MaterialButton>(R.id.btn_dialog_sim)

        btnNao?.setOnClickListener {
            dialog.dismiss()
        }

        btnSim?.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Edição salva com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        }

        dialog.show()
    }
}
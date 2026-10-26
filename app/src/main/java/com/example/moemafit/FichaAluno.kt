package com.example.moemafit

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class FichaAlunoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ficha_aluno_achado)

        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_detalhes_coletiva)
        btnVoltar.setOnClickListener {
            finish()
        }

        val btnEditar = findViewById<MaterialButton>(R.id.btn_editar)
        btnEditar.setOnClickListener {
            startActivity(Intent(this, EditarFichaActivity::class.java))
        }

        val btnExcluir = findViewById<MaterialButton>(R.id.btn_excluir)
        btnExcluir.setOnClickListener {
            abrirDialogExcluir()
        }
    }

    private fun abrirDialogExcluir() {
        val dialog = Dialog(this)

        // Nome do XML do aviso de exclusão vermelho
        dialog.setContentView(R.layout.dialog_confirmacao_excluir_ficha)

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val btnCancelar = dialog.findViewById<MaterialButton>(R.id.btn_dialog_cancelar)
        val btnConfirmarExcluir = dialog.findViewById<MaterialButton>(R.id.btn_dialog_excluir)

        btnCancelar?.setOnClickListener {
            dialog.dismiss()
        }

        btnConfirmarExcluir?.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Ficha excluída com sucesso!", Toast.LENGTH_SHORT).show()

            setResult(RESULT_OK, Intent().putExtra("ficha_excluida", true))
            finish()
        }

        dialog.show()
    }
}
package com.example.moemafit

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class PreCadastroAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_cadastro)

        val btnSalvar = findViewById<Button>(R.id.btn_salvar_pre_cadastro)
        btnSalvar.setOnClickListener {
            abrirDialogConfirmarCadastro()
        }

        val btnVoltar = findViewById<ImageView>(R.id.btn_voltar_detalhes_coletiva)
        btnVoltar?.setOnClickListener {
            finish()
        }
    }

    private fun abrirDialogConfirmarCadastro() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_finalizar_cadastro)

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val btnConfirmar = dialog.findViewById<MaterialButton>(R.id.btnConfirmarPreCadastro)
        val btnCancelar = dialog.findViewById<MaterialButton>(R.id.btnCancelarPreCadastro)

        btnConfirmar?.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Cadastro finalizado com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnCancelar?.setOnClickListener {
            dialog.dismiss()
            finish()
        }

        dialog.show()
    }
}
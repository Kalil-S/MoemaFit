package com.example.moemafit

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class DetalhesAulaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_coletiva_prof)

        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_aula_coletiva_prof)
        btnVoltar.setOnClickListener {
            finish()
        }

        val btnCancelarAula = findViewById<Button>(R.id.btnCancelarAula)
        btnCancelarAula.setOnClickListener {
            abrirDialogCancelamento()
        }
    }

    private fun abrirDialogCancelamento() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_cancelar_aula_coletiva)

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val btnConfirmar = dialog.findViewById<Button>(R.id.btnConfirmarCancelamentoAula)
        val btnVoltarCancelamento = dialog.findViewById<Button>(R.id.btnVoltarCancelamento)

        btnConfirmar?.setOnClickListener {
            dialog.dismiss()
            finish()
        }

        btnVoltarCancelamento?.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
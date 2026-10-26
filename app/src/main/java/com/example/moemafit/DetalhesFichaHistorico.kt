package com.example.moemafit

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetalhesFichaHistoricoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_ficha_historico_prof)

        findViewById<ImageButton>(
            R.id.btn_voltar_detalhes_coletiva
        ).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(
            R.id.btn_reativar
        ).setOnClickListener {
            mostrarDialogReativar()
        }

        findViewById<MaterialButton>(
            R.id.btn_excluir
        ).setOnClickListener {
            confirmarExclusao()
        }
    }

    private fun mostrarDialogReativar() {
        val dialog = Dialog(this)

        dialog.setContentView(
            R.layout.dialog_confirmacao_reativar
        )

        dialog.window?.setBackgroundDrawableResource(
            android.R.color.transparent
        )

        val btnCancelar = dialog.findViewById<MaterialButton>(
            R.id.btnCancelar
        )

        val btnReativar = dialog.findViewById<MaterialButton>(
            R.id.btnReativar
        )

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }

        btnReativar.setOnClickListener {
            val dataAtual = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            ).format(Date())

            val resultado = Intent().apply {
                putExtra("acao", "reativar")
                putExtra("data_alteracao", dataAtual)
            }

            setResult(RESULT_OK, resultado)

            dialog.dismiss()
            finish()

            Toast.makeText(
                this,
                "Ficha reativada com sucesso!",
                Toast.LENGTH_SHORT
            ).show()
        }

        dialog.show()

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun confirmarExclusao() {
        val dialogView = layoutInflater.inflate(
            R.layout.dialog_confirmacao_excluir_ficha,
            null
        )

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialog.window?.setBackgroundDrawableResource(
            android.R.color.transparent
        )

        dialogView.findViewById<Button>(R.id.btn_dialog_cancelar).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.btn_dialog_excluir).setOnClickListener {
            val resultado = Intent().apply {
                putExtra("acao", "excluir")
            }

            setResult(RESULT_OK, resultado)
            dialog.dismiss()
            finish()
        }

        dialog.show()
    }
}

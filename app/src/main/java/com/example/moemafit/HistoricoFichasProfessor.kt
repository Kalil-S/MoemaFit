package com.example.moemafit

import android.app.Activity
import android.graphics.Color
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class HistoricoFichasProfessorActivity : AppCompatActivity() {

    private val detalhesLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { resultado ->

            if (resultado.resultCode != Activity.RESULT_OK) {
                return@registerForActivityResult
            }

            val acao = resultado.data?.getStringExtra("acao")
                ?: return@registerForActivityResult

            when (acao) {
                "reativar" -> {
                    val data = resultado.data?.getStringExtra("data_alteracao")
                        ?: return@registerForActivityResult

                    atualizarFichaAlice(data)
                }

                "excluir" -> excluirFichaAlice()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico_fichas_professor)

        findViewById<ImageButton>(
            R.id.btn_voltar_detalhes_coletiva
        ).setOnClickListener {
            finish()
        }

        findViewById<MaterialCardView>(
            R.id.card_ficha_alice
        ).setOnClickListener {
            val intent = Intent(
                this,
                DetalhesFichaHistoricoActivity::class.java
            )

            detalhesLauncher.launch(intent)
        }
    }

    private fun atualizarFichaAlice(data: String) {

        findViewById<TextView>(
            R.id.txt_status_alice
        ).text = "ATIVA"

        findViewById<MaterialCardView>(
            R.id.badge_status_alice
        ).setCardBackgroundColor(
            Color.parseColor("#E6F4EA")
        )

        findViewById<TextView>(
            R.id.txt_status_alice
        ).setTextColor(
            Color.parseColor("#1E8E3E")
        )

        findViewById<TextView>(
            R.id.txt_data_alteracao_alice
        ).text = "Alterada: $data"

        Toast.makeText(
            this,
            "Ficha da Alice reativada!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun excluirFichaAlice() {
        val lista = findViewById<LinearLayout>(
            R.id.lista_fichas
        )

        val cardAlice = findViewById<MaterialCardView>(
            R.id.card_ficha_alice
        )

        lista.removeView(cardAlice)

        Toast.makeText(
            this,
            "Ficha removida do histórico.",
            Toast.LENGTH_SHORT
        ).show()
    }
}

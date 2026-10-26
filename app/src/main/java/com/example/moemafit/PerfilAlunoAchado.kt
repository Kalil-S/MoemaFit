package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class PerfilAlunoActivity : AppCompatActivity() {

    private lateinit var cardFicha: MaterialCardView
    private lateinit var cardEmptyState: MaterialCardView
    private lateinit var txtDataAlteracao: TextView

    private val fichaLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK &&
            result.data?.getBooleanExtra("ficha_excluida", false) == true
        ) {
            mostrarSemFicha()
        }
    }

    private val criarFichaLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK &&
            result.data?.getBooleanExtra("ficha_criada", false) == true
        ) {
            mostrarComFicha()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aluno_achado)

        cardFicha = findViewById(R.id.card_ficha_carolina)
        cardEmptyState = findViewById(R.id.card_empty_state_fichas)
        txtDataAlteracao = findViewById(R.id.txt_data_alteracao)

        findViewById<ImageButton>(R.id.btn_voltar_detalhes_coletiva).setOnClickListener {
            finish()
        }

        cardFicha.setOnClickListener {
            fichaLauncher.launch(Intent(this, FichaAlunoActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.btn_criar_ficha_empty).setOnClickListener {
            criarFichaLauncher.launch(Intent(this, CriarFicha::class.java))
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val data = intent.getStringExtra("data_alteracao")
        if (data != null) {
            txtDataAlteracao.text = "Alterada: $data"
            txtDataAlteracao.visibility = View.VISIBLE
        }
    }

    private fun mostrarSemFicha() {
        cardFicha.visibility = View.GONE
        cardEmptyState.visibility = View.VISIBLE
    }

    private fun mostrarComFicha() {
        cardFicha.visibility = View.VISIBLE
        cardEmptyState.visibility = View.GONE
        txtDataAlteracao.visibility = View.GONE // ficha nova, ainda sem alteração
    }
}
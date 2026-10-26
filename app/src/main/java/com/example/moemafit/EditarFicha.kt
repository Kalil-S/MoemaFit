package com.example.moemafit

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditarFichaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_ficha)

        findViewById<ImageButton>(R.id.btn_voltar_detalhes_coletiva).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btn_cancelar).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btn_salvar_pre_cadastro).setOnClickListener {
            abrirDialogSalvar()
        }

        val cardTreinoA = findViewById<MaterialCardView>(R.id.card_editar_treino_a)
        val cardTreinoB = findViewById<MaterialCardView>(R.id.card_editar_treino_b)
        val cardTreinoC = findViewById<MaterialCardView>(R.id.card_editar_treino_c)

        cardTreinoA.setOnClickListener { abrirEdicaoTreino(cardTreinoA, "Treino A") }
        cardTreinoB.setOnClickListener { abrirEdicaoTreino(cardTreinoB, "Treino B") }
        cardTreinoC.setOnClickListener { abrirEdicaoTreino(cardTreinoC, "Treino C") }
    }

    private fun dp(valor: Int) = (valor * resources.displayMetrics.density).toInt()

    private fun abrirEdicaoTreino(card: MaterialCardView, titulo: String) {
        val conteudo = card.getChildAt(0) as LinearLayout

        // Pega só as linhas de exercício (5 colunas, ignorando o cabeçalho da tabela)
        val linhas = mutableListOf<LinearLayout>()
        for (i in 0 until conteudo.childCount) {
            val filho = conteudo.getChildAt(i)
            if (filho is LinearLayout &&
                filho.orientation == LinearLayout.HORIZONTAL &&
                filho.childCount == 5
            ) {
                val primeiro = filho.getChildAt(0)
                if (primeiro is TextView && primeiro.text.toString() != "Exercício") {
                    linhas.add(filho)
                }
            }
        }

        val form = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(20), dp(8), dp(20), dp(8))
        }

        val dicas = listOf("Série", "Rep.", "Carga", "Desc.")
        val campos = mutableListOf<List<EditText>>()

        linhas.forEach { linha ->
            val textos = (0 until 5).map { linha.getChildAt(it) as TextView }

            val campoNome = EditText(this).apply {
                hint = "Exercício"
                setText(textos[0].text)
                inputType = InputType.TYPE_CLASS_TEXT
            }

            val linhaNumeros = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(0, 0, 0, dp(16))
            }

            val camposNumeros = (1..4).map { indice ->
                EditText(this).apply {
                    hint = dicas[indice - 1]
                    setText(textos[indice].text)
                    gravity = Gravity.CENTER
                    inputType = if (indice <= 2) {
                        InputType.TYPE_CLASS_NUMBER
                    } else {
                        InputType.TYPE_CLASS_TEXT
                    }
                    layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                }
            }

            camposNumeros.forEach { linhaNumeros.addView(it) }
            form.addView(campoNome)
            form.addView(linhaNumeros)

            campos.add(listOf(campoNome) + camposNumeros)
        }

        val scroll = ScrollView(this).apply { addView(form) }

        AlertDialog.Builder(this)
            .setTitle("Editar $titulo")
            .setView(scroll)
            .setPositiveButton("Aplicar") { _, _ ->
                linhas.forEachIndexed { i, linha ->
                    campos[i].forEachIndexed { j, campo ->
                        val novoTexto = campo.text.toString().trim().ifEmpty { "-" }
                        (linha.getChildAt(j) as TextView).text = novoTexto
                    }
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
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

            val data = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).format(Date())
            Toast.makeText(this, "Edição salva com sucesso!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, PerfilAlunoActivity::class.java)
            intent.putExtra("data_alteracao", data)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        dialog.show()
    }
}
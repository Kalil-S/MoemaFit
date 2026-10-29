package com.example.moemafit

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EmTreinoAlunoActivity : AppCompatActivity() {

    private val treinosData = mapOf(
        "A" to listOf(
            Triple("Cadeira Extensora", 3, "12x"),
            Triple("Cadeira Flexora", 3, "12x"),
            Triple("Cadeira Abdutora", 3, "12x"),
            Triple("Puxador Triangular", 3, "12x")
        ),
        "B" to listOf(
            Triple("Supino Reto", 4, "10x"),
            Triple("Puxada Alta", 3, "12x"),
            Triple("Desenvolvimento", 3, "10x")
        ),
        "C" to listOf(
            Triple("Prancha Abdominal", 3, "1 min"),
            Triple("Esteira (Cardio)", 1, "20 min")
        )
    )

    private var treinoAtualKey = "A"
    private var exercicioSelecionadoIndex = 0

    private lateinit var txtNomeExercicioAtivo: TextView
    private lateinit var layoutListaOutrosExercicios: LinearLayout
    private lateinit var txtTituloTreinoTopo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_em_treino_aluno)

        treinoAtualKey = intent.getStringExtra("TREINO_SELECIONADO") ?: "A"

        txtNomeExercicioAtivo = findViewById(R.id.txtNomeExercicioAtivo)
        layoutListaOutrosExercicios = findViewById(R.id.layoutListaOutrosExercicios)
        txtTituloTreinoTopo = findViewById(R.id.txtTituloTreinoTopo)

        txtTituloTreinoTopo.text = "Em Treino - Treino $treinoAtualKey"

        // --- 1. Botão Voltar (Seta superior esquerda) -> Retorna para "Minha Ficha" ---
        findViewById<ImageButton>(R.id.btnVoltarEmTreino)?.setOnClickListener {
            val intent = Intent(this, MinhaFichaAlunoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        // --- 2. Botão Problemas? -> Abre a "FeedbackAlunoActivity" ---
        findViewById<MaterialButton>(R.id.btnProblemas)?.setOnClickListener {
            val intent = Intent(this, FeedbackAlunoActivity::class.java)
            startActivity(intent)
        }

        // --- 3. Botão Finalizar -> Abre a tela de Treino Concluído ---
        findViewById<MaterialButton>(R.id.btnFinalizarTreino)?.setOnClickListener {
            Toast.makeText(this, "Treino $treinoAtualKey concluído com sucesso!", Toast.LENGTH_SHORT).show()

            // Vai para a tela de parabéns/concluído
            val intent = Intent(this, TreinoConcluidoActivity::class.java)
            startActivity(intent)
            finish()
        }

        carregarInterfaceTreino()
    }

    private fun carregarInterfaceTreino() {
        val listaExercicios = treinosData[treinoAtualKey] ?: return
        if (exercicioSelecionadoIndex >= listaExercicios.size) {
            exercicioSelecionadoIndex = 0
        }

        // Atualizar o exercício em destaque no topo
        val ativo = listaExercicios[exercicioSelecionadoIndex]
        txtNomeExercicioAtivo.text = ativo.first

        // Preencher a lista inferior com os restantes exercícios
        layoutListaOutrosExercicios.removeAllViews()

        for ((index, exercicio) in listaExercicios.withIndex()) {
            if (index == exercicioSelecionadoIndex) continue

            val row = LinearLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 6, 0, 6) }
                orientation = LinearLayout.HORIZONTAL
                setPadding(8, 10, 8, 10)
                isClickable = true
                isFocusable = true
            }

            val tvNome = TextView(this).apply {
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.2f)
                text = exercicio.first
                setTextColor(resources.getColor(android.R.color.white, theme))
                textSize = 14f
            }

            val tvSeries = TextView(this).apply {
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.8f)
                text = exercicio.second.toString()
                setTextColor(resources.getColor(android.R.color.white, theme))
                textSize = 14f
                gravity = Gravity.CENTER
            }

            val tvRep = TextView(this).apply {
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.8f)
                text = exercicio.third
                setTextColor(resources.getColor(android.R.color.white, theme))
                textSize = 14f
                gravity = Gravity.CENTER
            }

            val tvDesc = TextView(this).apply {
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.2f)
                text = "30 Seg"
                setTextColor(resources.getColor(android.R.color.white, theme))
                textSize = 14f
                gravity = Gravity.END
            }

            row.addView(tvNome)
            row.addView(tvSeries)
            row.addView(tvRep)
            row.addView(tvDesc)

            row.setOnClickListener {
                exercicioSelecionadoIndex = index
                carregarInterfaceTreino()
            }

            layoutListaOutrosExercicios.addView(row)
        }
    }
}
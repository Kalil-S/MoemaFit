
package com.example.moemafit

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.util.Calendar
import java.util.Locale

class CriarFicha : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_ficha)

        // Botão de voltar no topo
        findViewById<View>(R.id.button_sair_ficha)
            .setOnClickListener {
                finish()
            }

        // Adicionar exercícios da ficha A
        findViewById<MaterialButton>(R.id.btn_add_exercicio_a)
            .setOnClickListener {
                adicionarExercicio(R.id.container_exercicios_a)
            }

        // Adicionar exercícios da ficha B
        findViewById<MaterialButton>(R.id.btn_add_exercicio_b)
            .setOnClickListener {
                adicionarExercicio(R.id.container_exercicios_b)
            }

        // Adicionar exercícios da ficha C
        findViewById<MaterialButton>(R.id.btn_add_exercicio_c)
            .setOnClickListener {
                adicionarExercicio(R.id.container_exercicios_c)
            }

        // Botão Enviar
        findViewById<MaterialButton>(R.id.btn_enviar)
            .setOnClickListener {
                mostrarDialogSucesso()
            }

        // Botão Cancelar: fecha a tela imediatamente
        findViewById<MaterialButton>(R.id.btn_cancelar)
            .setOnClickListener {
                finish()
            }

        // Selecionar a data de validade
        val campoValidade =
            findViewById<EditText>(R.id.edit_validade)

        campoValidade.setOnClickListener {
            val calendario = Calendar.getInstance()

            DatePickerDialog(
                this,
                { _, ano, mes, dia ->
                    campoValidade.setText(
                        String.format(
                            Locale.getDefault(),
                            "%02d/%02d/%04d",
                            dia,
                            mes + 1,
                            ano
                        )
                    )
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun adicionarExercicio(containerId: Int) {

        val container =
            findViewById<LinearLayout>(containerId)

        val linha = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(4, 4, 4, 4)
        }

        fun criarCampo(
            dica: String,
            peso: Float,
            tipo: Int
        ): EditText {
            return EditText(this).apply {
                hint = dica
                inputType = tipo
                textSize = 14f
                gravity = Gravity.CENTER
                setSingleLine(true)
                background = null

                layoutParams = LinearLayout.LayoutParams(
                    0,
                    dp(40),
                    peso
                )
            }
        }

        // Nome do exercício
        linha.addView(
            criarCampo(
                "Nome",
                2.5f,
                android.text.InputType.TYPE_CLASS_TEXT
            )
        )

        // Séries
        linha.addView(
            criarCampo(
                "0",
                1f,
                android.text.InputType.TYPE_CLASS_NUMBER
            )
        )

        // Repetições
        linha.addView(
            criarCampo(
                "0",
                1f,
                android.text.InputType.TYPE_CLASS_NUMBER
            )
        )

        // Carga
        linha.addView(
            criarCampo(
                "0.0",
                1f,
                android.text.InputType.TYPE_CLASS_NUMBER or
                        android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
            )
        )

        // Tempo de descanso
        linha.addView(
            criarCampo(
                "00",
                1f,
                android.text.InputType.TYPE_CLASS_NUMBER
            )
        )

        // Unidade do descanso
        val spinner = Spinner(this)

        val opcoes = listOf("seg", "min")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            opcoes
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinner.adapter = adapter

        linha.addView(
            spinner,
            LinearLayout.LayoutParams(
                dp(60),
                dp(40)
            )
        )

        val parametros = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        parametros.bottomMargin = dp(8)

        container.addView(linha, parametros)
    }

    private fun mostrarDialogSucesso() {
        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_sucesso_ficha)

        dialog.window?.setBackgroundDrawableResource(
            android.R.color.transparent
        )

        val btnOk = dialog.findViewById<MaterialButton>(
            R.id.btn_dialog_ok
        )

        btnOk.setOnClickListener {
            dialog.dismiss()
            finish()
        }

        dialog.show()

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun dp(valor: Int): Int {
        return (valor * resources.displayMetrics.density).toInt()
    }
}

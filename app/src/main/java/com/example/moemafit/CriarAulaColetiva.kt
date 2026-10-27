package com.example.moemafit

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class CriarAulaColetivaActivity : AppCompatActivity() {

    private lateinit var edtData: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_aula_coletiva)

        findViewById<ImageButton>(R.id.btn_voltar).setOnClickListener {
            finish()
        }

        edtData = findViewById(R.id.edtData)
        val layoutData = findViewById<TextInputLayout>(R.id.layoutData)

        layoutData.setEndIconOnClickListener {
            abrirCalendario()
        }

        edtData.setOnClickListener {
            abrirCalendario()
        }

        findViewById<MaterialButton>(R.id.btnSalvar).setOnClickListener {
            abrirDialogSucesso()
        }
    }

    private fun abrirCalendario() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Selecione a data da aula")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        picker.addOnPositiveButtonClickListener { selecao ->
            // O DatePicker devolve a data em UTC; formatar em UTC evita mostrar o dia anterior
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
            formato.timeZone = TimeZone.getTimeZone("UTC")
            edtData.setText(formato.format(Date(selecao)))
        }

        picker.show(supportFragmentManager, "date_picker")
    }

    private fun abrirDialogSucesso() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_sucesso_criacao_aula_coletiva)

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.setCancelable(false)

        val btnOk = dialog.findViewById<MaterialButton>(R.id.btnOkSucesso)
        btnOk?.setOnClickListener {
            dialog.dismiss()
            finish()
        }

        dialog.show()
    }
}
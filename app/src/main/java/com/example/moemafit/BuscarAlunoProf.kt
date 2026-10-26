package com.example.moemafit

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class BuscarAlunoActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private var segurouCincoSegundos = false

    private val abrirNaoEncontrado = Runnable {
        segurouCincoSegundos = true
        abrirDialogAlunoNaoEncontrado()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_aluno)

        val btnVoltar = findViewById<ImageButton>(R.id.btn_voltar_detalhes_coletiva)
        btnVoltar.setOnClickListener {
            finish()
        }

        val btnBuscar = findViewById<MaterialButton>(R.id.btn_buscar)

        btnBuscar.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    segurouCincoSegundos = false
                    handler.postDelayed(abrirNaoEncontrado, 3000)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    handler.removeCallbacks(abrirNaoEncontrado)
                    if (!segurouCincoSegundos) {
                        view.performClick()
                        val intent = Intent(this, PerfilAlunoActivity::class.java)
                        startActivity(intent)
                    }
                    true
                }
                MotionEvent.ACTION_CANCEL -> {
                    handler.removeCallbacks(abrirNaoEncontrado)
                    true
                }
                else -> false
            }
        }
    }

    private fun abrirDialogAlunoNaoEncontrado() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_aluno_nao_encontrado) // nome do seu arquivo XML

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val btnOk = dialog.findViewById<MaterialButton>(R.id.btnOkNaoEncontrado)
        btnOk.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(abrirNaoEncontrado)
    }
}
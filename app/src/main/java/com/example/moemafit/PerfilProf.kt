package com.example.moemafit

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class PerfilProfessorActivity : AppCompatActivity() {

    private lateinit var fotoProfessor: ImageView

    private val tirarFotoLauncher =
        registerForActivityResult(
            ActivityResultContracts.TakePicturePreview()
        ) { bitmap: Bitmap? ->

            if (bitmap != null) {
                fotoProfessor.setImageBitmap(bitmap)

                Toast.makeText(
                    this,
                    "Foto de perfil atualizada!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Nenhuma foto foi capturada.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private val galeriaLauncher =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->

            if (uri != null) {
                fotoProfessor.setImageURI(uri)

                Toast.makeText(
                    this,
                    "Foto de perfil atualizada!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_professor)

        fotoProfessor = findViewById(R.id.img_perfil_professor)

        findViewById<ImageButton>(
            R.id.btn_voltar_detalhes_coletiva
        ).setOnClickListener {
            finish()
        }

        findViewById<FrameLayout>(
            R.id.btn_alterar_foto
        ).setOnClickListener {
            mostrarBottomSheetFoto()
        }

        findViewById<LinearLayout>(
            R.id.btn_historico_fichas
        ).setOnClickListener {
            val intent = Intent(
                this,
                HistoricoFichasProfessorActivity::class.java
            )
            startActivity(intent)
        }
        findViewById<LinearLayout>(
            R.id.btn_redefinir_senha
        ).setOnClickListener {
            val intent = android.content.Intent(
                this,
                RedefinirSenhaActivity::class.java
            )
            startActivity(intent)
        }
    }

    private fun mostrarBottomSheetFoto() {
        val bottomSheet = BottomSheetDialog(this)

        val view = LayoutInflater.from(this).inflate(
            R.layout.bottom_sheet_escolher_foto,
            null
        )

        bottomSheet.setContentView(view)

        view.findViewById<LinearLayout>(
            R.id.btn_tirar_foto
        ).setOnClickListener {
            bottomSheet.dismiss()
            tirarFotoLauncher.launch(null)
        }

        view.findViewById<LinearLayout>(
            R.id.btn_buscar_galeria
        ).setOnClickListener {
            bottomSheet.dismiss()
            galeriaLauncher.launch("image/*")
        }

        bottomSheet.show()
    }
}

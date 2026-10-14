package com.example.moemafit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetalhesAulaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_aula)

        findViewById<View>(R.id.btnVoltar).setOnClickListener { finish() }

        findViewById<View>(R.id.btnParticipar).setOnClickListener {
            // Aqui entraria o registro da presença no servidor numa etapa futura
            Toast.makeText(this, "Participação confirmada", Toast.LENGTH_SHORT).show()
        }
    }
}

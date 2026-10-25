package com.example.moemafit

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit

class ConfiguracoesActivity : AppCompatActivity() {

    private val CHANNEL_ID = "moemafit_notificacoes"
    private val PERMISSAO_NOTIFICACAO_CODE = 112 // Um código qualquer para identificar o pedido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)

        // 1. Pede a permissão pro usuário se o Android for 13 ou superior
        pedirPermissaoNotificacao()

        // 2. Cria o canal de notificações
        criarCanalNotificacao()

        findViewById<View>(R.id.btnVoltar).setOnClickListener { finish() }

        findViewById<View>(R.id.txtRedefinirSenha).setOnClickListener {
            startActivity(Intent(this, RedefinirSenhaActivity::class.java))
        }

        val prefs = getSharedPreferences("config", Context.MODE_PRIVATE)
        val swFeedback = findViewById<SwitchCompat>(R.id.switchFeedbacks)
        val swCalendario = findViewById<SwitchCompat>(R.id.switchCalendario)

        swFeedback.isChecked = prefs.getBoolean("desativar_feedbacks", false)
        swCalendario.isChecked = prefs.getBoolean("desativar_calendario", false)

        swFeedback.setOnCheckedChangeListener { _, marcado ->
            prefs.edit().putBoolean("desativar_feedbacks", marcado).apply()

            if (marcado) {
                dispararNotificacao(
                    notificacaoId = 101,
                    titulo = "Novo Feedback de Aluno",
                    mensagem = "Matheus Nunes S. reportou: Esteira danificada."
                )
            }
        }

        swCalendario.setOnCheckedChangeListener { _, marcado ->
            prefs.edit { putBoolean("desativar_calendario", marcado) }

            if (marcado) {
                dispararNotificacao(
                    notificacaoId = 102,
                    titulo = "Lembrete de Aula",
                    mensagem = "Sua aula de Spinning começa amanhã às 18h na Sala 4."
                )
            }
        }
    }

    private fun pedirPermissaoNotificacao() {
        // Verifica se é Android 13 (Tiramisu) ou superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Verifica se a permissão já foi concedida antes
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Se não foi concedida, abre a janelinha perguntando
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSAO_NOTIFICACAO_CODE
                )
            }
        }
    }

    private fun criarCanalNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nome = "Notificações MoemaFit"
            val descricao = "Canal para lembretes de aulas e feedbacks"
            val importancia = NotificationManager.IMPORTANCE_DEFAULT

            val canal = NotificationChannel(CHANNEL_ID, nome, importancia).apply {
                description = descricao
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(canal)
        }
    }

    private fun dispararNotificacao(notificacaoId: Int, titulo: String, mensagem: String) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setContentTitle(titulo)
            .setContentText(mensagem)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        try {
            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(notificacaoId, builder.build())
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}
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
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit

class ConfiguracoesAlunoActivity : AppCompatActivity() {

    private val CHANNEL_ID = "moemafit_notificacoes_aluno"
    private val PERMISSAO_NOTIFICACAO_CODE = 113

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes_aluno)

        // Pede a permissão de notificação no Android 13+
        pedirPermissaoNotificacao()
        criarCanalNotificacao()

        // Botão de voltar (seta) - Fecha esta tela e retorna ao Menu Aluno
        // Certifica-te de que no teu XML o botão de voltar tem o ID btnVoltar (ou ajusta para o ID correto)
        findViewById<View>(R.id.btnVoltar)?.setOnClickListener { finish() }

        // Redirecionar para redefinir senha (caso o aluno queira)
        findViewById<View>(R.id.txtRedefinirSenha)?.setOnClickListener {
            startActivity(Intent(this, RedefinirSenhaActivity::class.java))
        }

        val prefs = getSharedPreferences("config_aluno", Context.MODE_PRIVATE)
        val swNotificacoes = findViewById<SwitchCompat>(R.id.switchFeedbacks) // Ou o ID do switch do teu XML de aluno
        val swCalendario = findViewById<SwitchCompat>(R.id.switchCalendario)

        swNotificacoes?.isChecked = prefs.getBoolean("notificacoes_ativadas", true)
        swCalendario?.isChecked = prefs.getBoolean("lembrete_calendario", true)

        swNotificacoes?.setOnCheckedChangeListener { _, marcado ->
            prefs.edit().putBoolean("notificacoes_ativadas", marcado).apply()
            if (marcado) {
                dispararNotificacao(
                    notificacaoId = 201,
                    titulo = "MoemaFit",
                    mensagem = "As notificações de avisos da academia estão ativadas."
                )
            }
        }

        swCalendario?.setOnCheckedChangeListener { _, marcado ->
            prefs.edit { putBoolean("lembrete_calendario", marcado) }
            if (marcado) {
                dispararNotificacao(
                    notificacaoId = 202,
                    titulo = "Lembrete de Treino",
                    mensagem = "Avisaremos sobre os dias de aulas e treinos marcados."
                )
            }
        }
    }

    private fun pedirPermissaoNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
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
            val nome = "Notificações Aluno MoemaFit"
            val descricao = "Canal para lembretes do aluno"
            val importancia = NotificationManager.IMPORTANCE_DEFAULT

            val canal = NotificationChannel(CHANNEL_ID, nome, importancia).apply {
                description = descricao
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
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
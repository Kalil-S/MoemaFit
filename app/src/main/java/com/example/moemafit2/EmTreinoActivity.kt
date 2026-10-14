
package com.example.moemafit2

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.moemafit2.ui.theme.MoemaFit2Theme

class EmTreinoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoemaFit2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EmTreinoScreen(
                        modifier = Modifier.padding(innerPadding),
                        onVoltar = {
                            // Mostra apenas a notificação ao clicar na seta, sem fechar a tela
                            mostrarNotificacao(
                                this,
                                3,
                                "Seta Pressionada",
                                "Você clicou no botão de voltar!"
                            )
                        }
                    )
                }
            }
        }
    }
}

// Cores da tela
private val TreinoAzulFundo = Color(0xFF141F8C)
private val TreinoCinza = Color(0xFF8A8F99)
private val TreinoAzulClaro = Color(0xFFE3F2FD)
private const val CANAL_ID = "canal_treino"

// Função para disparar a notificação no Android
@SuppressLint("MissingPermission")
private fun mostrarNotificacao(context: Context, id: Int, titulo: String, texto: String) {
    if (Build.VERSION.SDK_INT >= 33 &&
        ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
        != PackageManager.PERMISSION_GRANTED
    ) {
        Toast.makeText(
            context,
            "Permita as notificações nas configurações do app",
            Toast.LENGTH_SHORT
        ).show()
        return
    }

    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val canal = NotificationChannel(CANAL_ID, "Treino", NotificationManager.IMPORTANCE_HIGH)
    manager.createNotificationChannel(canal)

    val notificacao = NotificationCompat.Builder(context, CANAL_ID)
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle(titulo)
        .setContentText(texto)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .build()

    manager.notify(id, notificacao)
}

@Composable
fun EmTreinoScreen(modifier: Modifier = Modifier, onVoltar: () -> Unit = {}) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TreinoAzulFundo)
    ) {
        // Barra superior branca com o botão da seta
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onVoltar) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            // Faixa "Treino A"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .background(TreinoCinza),
                contentAlignment = Alignment.Center
            ) {
                Text("Treino A", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Aparelho + Nome
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(width = 48.dp, height = 36.dp)
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Cadeira Extensora",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Serif
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Cartões de Séries
            CartaoSerie("1º Série", "12x", "Des 30s")
            Spacer(modifier = Modifier.height(20.dp))
            CartaoSerie("2º Série", "12x", "Des 30s")
            Spacer(modifier = Modifier.height(20.dp))
            CartaoSerie("3º Série", "12x", "Des 30s")

            Spacer(modifier = Modifier.height(16.dp))

            // Lista dos outros exercícios
            LinhaExercicio("Cadeira Flexora", "3", "12", "30 Seg")
            LinhaExercicio("Cadeira Abdutora", "3", "12", "30 Seg")
            LinhaExercicio("Puxador Triangular", "3", "12", "30 Seg")

            Spacer(modifier = Modifier.height(16.dp))

            // Botão "Problemas?"
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = {
                        mostrarNotificacao(
                            context, 1,
                            "Problemas?",
                            "Botão Problemas? pressionado!"
                        )
                    },
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TreinoCinza,
                        contentColor = Color(0xFF8B0000)
                    ),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("Problemas?", fontFamily = FontFamily.Serif, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Botão "Finalizar"
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = {
                        mostrarNotificacao(
                            context, 2,
                            "Finalizar",
                            "Botão Finalizar pressionado!"
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TreinoAzulClaro,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(52.dp)
                ) {
                    Text("Finalizar", fontFamily = FontFamily.Serif, fontSize = 24.sp)
                }
            }
        }
    }
}

// Componente para o cartão branco de cada série
@Composable
private fun CartaoSerie(serie: String, repeticoes: String, descanso: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            serie,
            color = Color.Black,
            fontFamily = FontFamily.Serif,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            repeticoes,
            color = Color.Black,
            fontFamily = FontFamily.Serif,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            descanso,
            color = Color.Black,
            fontFamily = FontFamily.Serif,
            fontSize = 14.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

// Componente para a linha informativa dos exercícios
@Composable
private fun LinhaExercicio(nome: String, series: String, reps: String, descanso: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            nome,
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontSize = 12.sp,
            modifier = Modifier.weight(2f)
        )
        Text(
            series,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.6f)
        )
        Text(
            reps,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.6f)
        )
        Text(
            descanso,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmTreinoScreenPreview() {
    MoemaFit2Theme {
        EmTreinoScreen()
    }
}
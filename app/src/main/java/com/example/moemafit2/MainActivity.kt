package com.example.moemafit2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moemafit2.ui.theme.MoemaFit2Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoemaFit2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CriarAulaScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Cores da tela (aproximadas do Figma)
private val AzulFundo = Color(0xFF141F8C)
private val AzulCampo = Color(0xFF3F4A9E)
private val AzulBarra = Color(0xFFE3F2FD)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriarAulaScreen(modifier: Modifier = Modifier) {
    // Guardam o que a pessoa digita. Quando mudam, a tela se atualiza sozinha
    var nomeAula by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var professor by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var sala by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var mostrarCalendario by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Janela do calendário (dia/mês/ano). Só aparece quando mostrarCalendario = true
    if (mostrarCalendario) {
        val estadoData = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { mostrarCalendario = false },
            confirmButton = {
                TextButton(onClick = {
                    estadoData.selectedDateMillis?.let { ms ->
                        val formato = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
                        formato.timeZone = TimeZone.getTimeZone("UTC")
                        data = formato.format(Date(ms))
                    }
                    mostrarCalendario = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { mostrarCalendario = false }) { Text("Cancelar") }
            }
        ) {
            DatePicker(state = estadoData)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AzulFundo)
    ) {
        // Barra de cima com a seta
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(AzulBarra),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                Toast.makeText(context, "Seta pressionada!", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        // Formulário (rola se a tela for pequena)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Aula Coletiva",
                color = Color.White,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Campo("Nome da aula", nomeAula, { nomeAula = it })
            Campo("E-mail de contato", email, { email = it }, KeyboardType.Email)
            Campo("Professor", professor, { professor = it })

            // Data: tocar no texto "Data" ou no campo abre o calendário
            Rotulo("Data", onClick = { mostrarCalendario = true })
            Box(modifier = Modifier.fillMaxWidth(0.75f)) {
                TextField(
                    value = data,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true,
                    trailingIcon = {
                        Icon(Icons.Filled.DateRange, contentDescription = "Calendário")
                    },
                    colors = coresCampo(),
                    modifier = Modifier.fillMaxWidth()
                )
                // Camada invisível por cima do campo, que captura o toque
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { mostrarCalendario = true }
                )
            }

            Campo("Sala", sala, { sala = it })
            Campo("Quantidade de pessoas", quantidade, { quantidade = it }, KeyboardType.Number)

            Spacer(modifier = Modifier.height(32.dp))

            // Botão Salvar
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        Toast.makeText(context, "Botão Salvar pressionado!", Toast.LENGTH_SHORT).show()
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .width(120.dp)
                        .height(44.dp)
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}

// Texto branco em cima do campo (ex.: "Nome da aula")
@Composable
private fun Rotulo(texto: String, onClick: (() -> Unit)? = null) {
    val base = Modifier.padding(top = 16.dp, bottom = 4.dp)
    Text(
        text = texto,
        color = Color.White,
        fontSize = 16.sp,
        modifier = if (onClick != null) base.clickable { onClick() } else base
    )
}

// Rótulo + campo onde dá para escrever (usado várias vezes)
@Composable
private fun Campo(
    rotulo: String,
    valor: String,
    aoMudar: (String) -> Unit,
    teclado: KeyboardType = KeyboardType.Text
) {
    Rotulo(rotulo)
    TextField(
        value = valor,
        onValueChange = aoMudar,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = teclado),
        colors = coresCampo(),
        modifier = Modifier.fillMaxWidth(0.75f)
    )
}

// Cores dos campos: fundo azul-claro, texto branco, sem linha embaixo
@Composable
private fun coresCampo() = TextFieldDefaults.colors(
    focusedContainerColor = AzulCampo,
    unfocusedContainerColor = AzulCampo,
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    cursorColor = Color.White,
    focusedTrailingIconColor = Color.White,
    unfocusedTrailingIconColor = Color.White
)

@Preview(showBackground = true)
@Composable
fun CriarAulaScreenPreview() {
    MoemaFit2Theme {
        CriarAulaScreen()
    }
}
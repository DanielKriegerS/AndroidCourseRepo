package com.danielks.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danielks.activities.ui.theme.ActivitiesTheme
import kotlin.random.Random

class Activity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivitiesTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize(),
                    containerColor = Color.White) { innerPadding ->
                    Atividade3(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.Black)
                    )
                }
            }
        }
    }

    @Composable
    fun Atividade3(modifier: Modifier){

        // Variáveis
        val numeroAleatorio = Random.nextInt(1, 101)
        var palpite by remember { mutableStateOf("") }
        var retorno by remember { mutableStateOf("") }
        var tentativas by remember { mutableStateOf(0) }

        // Estrutura
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            TextField(
                value = palpite,
                onValueChange = {palpite = it}
            )
            Button(
                onClick = {
                    // Contabilizar tentiva
                    tentativas++

                    // Diferença entre o palpite e o número gerado
                    val diferenca = kotlin.math.abs(palpite.toInt() - numeroAleatorio)

                    // Retorno
                    if(palpite.toInt() == numeroAleatorio){
                        retorno = "Acertou!!! Você precisou de $tentativas tentativas"
                    }else{

                        if(diferenca <= 5)
                            retorno = "Muito perto!"
                        else if(diferenca <= 10)
                            retorno = "Perto!"
                        else if(diferenca <= 15)
                            retorno = "Longe!"
                        else
                            retorno = "Muito longe!"
                    }
                }
            ) {
                Text("Vamos lá!")
            }
            Text(retorno)
        }


    }
}


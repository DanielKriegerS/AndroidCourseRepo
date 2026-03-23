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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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

class Activity1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivitiesTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize(),
                    containerColor = Color.Black) { innerPadding ->
                    Atividade1(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.Black)
                    )
                }
            }
        }
    }

    @Composable
    fun Atividade1(modifier: Modifier){

        // Variáveis
        var nota1 by remember { mutableStateOf("") }
        var nota2 by remember { mutableStateOf("") }
        var nota3 by remember { mutableStateOf("") }
        var media by remember { mutableDoubleStateOf(0.0) }
        var situacao by remember { mutableStateOf("") }
        var imagem by remember { mutableIntStateOf(0) }

        // Estrutura
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = nota1,
                onValueChange = {nota1 = it},
                label = {Text("Informe a 1ª nota")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            TextField(
                value = nota2,
                onValueChange = {nota2 = it},
                label = {Text("Informe a 2ª nota")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            TextField(
                value = nota3,
                onValueChange = {nota3 = it},
                label = {Text("Informe a 3ª nota")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            Button(
                onClick = {

                    // Realizar média
                    media = (nota1.toDouble() + nota2.toDouble() + nota3.toDouble()) / 3

                    // Verificar a situação e a imagem
                    if(media >= 7){
                        situacao = "Aprovado(a)"
                        imagem = R.drawable.aprovado
                    } else if(media >= 5){
                        situacao = "Recuperação"
                        imagem = R.drawable.recuperacao
                    } else {
                        situacao = "Reprovado(a)"
                        imagem = R.drawable.reprovado
                    }
                }
            ) {
                Text("Verificar situação")
            }

            // Caso seja realizada a média
            if(imagem != 0){
                Text(
                    text = "$situacao, com média: ${"%.2f".format(media)}",
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                Image(
                    painter = painterResource(id = imagem),
                    contentDescription = "Imagem da situação do aluno"
                )
            }

        }
    }
}


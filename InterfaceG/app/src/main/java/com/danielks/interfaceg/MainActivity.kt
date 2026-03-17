package com.danielks.interfaceg

import android.R.attr.label
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.danielks.interfaceg.ui.theme.InterfaceGTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterfaceGTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> // esse innerPadding é como se fosse o <HTML>
                    Component2(
                        modifier = Modifier.padding(innerPadding).background(Color.White)
                    )
                }
            }
        }
    }
}

/* AULA 11
// COMPONENTES
*/

@Composable
fun Component2(
    modifier: Modifier
) {
    // variavel
    var number by remember { mutableStateOf(0) }

    // estrutura
    //Text(number.toString()) // para qualquer coisa diferente de String, precisa do toString()
    Column (
        modifier = modifier
            .fillMaxSize()
            // .background(Color.LightGray), // cor das "defaults"
            .background(Color(0xFFA1042D)),
                // "0x" é o alpha, "FF" é o branco
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = number.toString(),
            color = Color.Black,
            fontSize = 50.sp
        )
        Row (
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Button(
//                modifier = modifier // usando modifier aqui, ele pega o lá da inicialização (com fundo branco)
//                    .padding(horizontal = 5.dp),
                onClick = {
                    number++
                },
                colors = ButtonDefaults
                    .buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    )
            ) {
                Text("Incrementar")
            }
            Button(
//                modifier = modifier
//                    .padding(horizontal = 5.dp),
                onClick = {
                    number--
                },
                colors = ButtonDefaults
                    .buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    )
            ) {
                Text("Decrementar")
            }
        }
    }
}

@Composable
fun Component1(
    modifier: Modifier
) {
    // var text : String = "Hello" // dessa forma a variável fica "estatica"
                                    //com relação a alterações em tela (render)
   // var text = remember { mutableStateOf("") } // assim precisa do .value pra acessar o valor

    var text by remember { mutableStateOf("") }
    Column (modifier = modifier
                .fillMaxSize()
        ){
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 35.sp,
            color = Color.Red,
            modifier = modifier.fillMaxWidth().padding(bottom = 20.dp)
        )
        // Text(text) // assim dá erro, precisa extrair o valor
        //Text(text)
        TextField( // equivale a um input
            value = text,
            onValueChange = {
                text = it
            },
//            label = {
  //              Text("Digite algo...")
    //        },
            placeholder =  {
                Text("Digite algo...")
            },
            modifier = Modifier
                .fillMaxWidth() // aqui gerou um novo modifier zerado
                .padding(horizontal = 20.dp) // pode definir de diversas formas (palavras especificas) qual padding vai alterar
        )
    }
}

/* Layouts
*  Box -> É o equivalente de uma div HTML (estrutura de agrupamento)
*  Row -> Estrutura de linhas
*  Column -> Estrutura de coluna
*  Pode ter um dentro do outro, dependendo do necessário no layout do projeto
*/

// sempre precisa do modifier, pois é ele quem faz a estilização (customização) dos componentes
@Composable
fun Structure1(modifier : Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize() // respeita toda a área da tela como parte do conteúdo
        //  .fillMaxHeight() // respeita apenas altura
        //  .fillMaxWidth() // respeita apenas largura
            .background(Color.White), // essa virgula finaliza o bloco do modifier, daqui pra baixo está falando de itens da box nativos
        //contentAlignment = Alignment.CenterEnd
    ) {
        Text("Meu primeiro layout", fontSize = 30.sp) // 1sp aproximadamente 1px
    }
}

@Composable
fun Structure2(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .background(Color.Blue)
                //.size(50.dp)
                .weight(2f) // delimita o "peso" desse elemento dentro do layout
        ) {Text("1")}
        Box(
            modifier = modifier
                .background(Color.Red)
                //.size(50.dp)
                .weight(1f)
        ) {Text("2")}
        Box(
            modifier = modifier
                .background(Color.Green)
                //.size(50.dp)
                .weight(1f)
        ) {Text("3")}
        Box(
            modifier = modifier
                .background(Color.Cyan)
                //.size(50.dp)
                .weight(1f)
        ) {Text("4")}
    }
}

@Composable
fun Structure3(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .background(Color.Blue)
                //.size(50.dp)
                .weight(1f) // delimita o "peso" desse elemento dentro do layout
                .fillMaxWidth()
            ) {Text("1")}
        Box(
            modifier = modifier
                .background(Color.Red)
                //.size(50.dp)
                .weight(1f)
                .fillMaxWidth()
            ) {Text("2")}
        Box(
            modifier = modifier
                .background(Color.Green)
                //.size(50.dp)
                .weight(1f)
                .fillMaxWidth()
            ) {Text("3")}
        Box(
            modifier = modifier
                .background(Color.Cyan)
                //.size(50.dp)
                .weight(1f)
                .fillMaxWidth()
            ) {Text("4")}
    }
}
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

class Activity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivitiesTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize(),
                    containerColor = Color.White) { innerPadding ->
                    Atividade2(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.Black)
                    )
                }
            }
        }
    }

    @Composable
    fun Atividade2(modifier: Modifier){

        // Variáveis
        var valor by remember { mutableStateOf("") }
        val formasPagamento = listOf<String>("Cartão de Crédito", "Cartão de Débito", "PIX", "TED", "Dinheiro")
        var formaPagamentoSelecionada by remember { mutableStateOf(formasPagamento[0]) }
        var pagamentoTotal by remember { mutableStateOf("") }

        // Estrutura
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = valor,
                onValueChange = {valor = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Column() {
                formasPagamento.forEach { forma ->
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = forma == formaPagamentoSelecionada,
                                onClick = { formaPagamentoSelecionada = forma}
                            )) {
                        RadioButton(
                            selected = forma == formaPagamentoSelecionada,
                            onClick = null
                        )
                        Text(forma)
                    }
                }
                Button(
                    onClick = {

                        if(formaPagamentoSelecionada == "Cartão de Crédito" || formaPagamentoSelecionada == "Cartão de Débito")
                            pagamentoTotal = "Não haverá desconto! Total a pagar R$ $valor"
                        else{
                            if(valor.toDouble() > 1000){
                                pagamentoTotal = "Haverá desconto! Total a pagar R$ ${valor.toDouble() * 0.9}"
                            }else{
                                pagamentoTotal = "Não haverá desconto! Total a pagar R$ $valor"
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                ) {
                    Text("Calcular")
                }
                Text(pagamentoTotal)
            }
        }

    }

}


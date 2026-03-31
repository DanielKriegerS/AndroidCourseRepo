package com.danielks.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danielks.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScreensManager(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// AULA 02



// AULA 01
@SuppressLint("RestrictedApi")
@Composable
fun ScreensManager(modifier: Modifier) {
    // nav controller (estrutura Stack)
    // controle de rotas e acesso ao histórico de movimentações entre telas
    val navController = rememberNavController()

    // Coluna
    Column () {
        // exibir histórico de rotas
        Box(

        ) {
            // obter nomes de telas / rotas acessadas
            val screens by navController.currentBackStack.collectAsState()

            // mapear telas
            val screensName = screens.mapNotNull { it.destination.route }

            // exibir
            Text(
                text = screensName.toString(),
                modifier = modifier
                    .padding(20.dp)
            )
        }
        // exibir telas
        NavHost(
            navController = navController,
            startDestination= "screen1"
        ) {
            composable("screen1") {
                Screen1(
                    showScreen2 = {
                        navController.navigate("Screen2")
                    },
                    showScreen3 = {
                        navController.navigate("Screen3/aprendendo a enviar parametros")
                    }
                )
            }

            composable("screen2") {
                Screen2(
                    showScreen1 = {
                        navController.navigate("Screen1")
                    },
                    showScreen3 = {
                        navController.navigate("Screen3/aprendendo a enviar parametros")
                    }
                )
            }

            composable("screen3/{parameter}") {
                // extraindo parametro
                backStackEntry ->
                    val parameter = backStackEntry.arguments?.getString("parameter")

                Screen3(
                    showScreen1 = {
                        navController.navigate("Screen1")
                    },
                    showScreen2 = {
                        navController.navigate("Screen2")
                    },
                    back = {
                        navController.navigate(navController.popBackStack())
                    },
                    showScreen1RemoveHist = {
                        navController.navigate("screen1") {
                            popUpTo(0)
                        }
                    },
                    text = parameter.toString()
                )
            }
        }
    }
}

@Composable
fun Screen1(
    showScreen2: () -> Unit,
    showScreen3: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Tela 1",
            fontSize = 25.sp
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Button(
            onClick = { showScreen2() }
        ) {
            Text("Tela 2")
        }

        Button(
            onClick = { showScreen3() }
        ) {
            Text("Tela 3")
        }
    }
}

@Composable
fun Screen2(
    showScreen1: () -> Unit,
    showScreen3: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Tela 2",
            fontSize = 25.sp
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Button(
            onClick = { showScreen1() }
        ) {
            Text("Tela 1")
        }

        Button(
            onClick = { showScreen3() }
        ) {
            Text("Tela 3")
        }
    }
}

@Composable
fun Screen3(
    showScreen1: () -> Unit,
    showScreen2: () -> Unit,
    back: () -> Unit,
    showScreen1RemoveHist: () -> Unit,
    text: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Tela 3",
            fontSize = 25.sp
        )

        Text(
            text = "Parametro recebido $text",
            fontSize = 25.sp,
            color = Color.Blue
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Button(
            onClick = { showScreen1() }
        ) {
            Text("Tela 1")
        }

        Button(
            onClick = { showScreen2() }
        ) {
            Text("Tela 2")
        }

        Button(
            onClick = { back() }
        ) {
            Text("Voltar")
        }

        Button(
            onClick = { showScreen1RemoveHist() }
        ) {
            Text("Tela 1 - Remover Histórico")
        }

    }
}
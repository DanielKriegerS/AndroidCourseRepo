package com.danielks.drawernavigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danielks.drawernavigation.ui.theme.DrawerNavigationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawerNavigationTheme {
                Manager()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Manager() {
    val navController = rememberNavController()

    // gerenciador de abertura e fechamento do menu
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    // é quem muda o valor do state
    val scope = rememberCoroutineScope()

    // estrutura do menu e conteúdo
    ModalNavigationDrawer(
        // verifica se inicia como open ou closed
        drawerState = drawerState,

        // estrutura/conteudo do menu
        drawerContent = {
            // menu horizontal
            ModalDrawerSheet {
                // titulo
//                Row (
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(12.dp)
//                        .background(Color(0xFF2196F3)),
  //                  verticalAlignment = Alignment.CenterVertically,
    //                horizontalArrangement = Arrangement.Center
//                ){
                    Text("Menu", modifier = Modifier.padding(12.dp))// , color = Color.White)

                    HorizontalDivider()
  //              }

                // link - inicio
                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = false,
                    onClick = {
                        // selecionar tela a ser exibida
                        navController.navigate("start") {
                            launchSingleTop = true
                        }

                        // fechar menu
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon (
                            Icons.Default.Home,
                            contentDescription = null,
                        )
                    }
                )

                // link - perfil
                NavigationDrawerItem(
                    label = { Text("Perfil") },
                    selected = false,
                    onClick = {
                        // selecionar tela a ser exibida
                        navController.navigate("profile") {
                            launchSingleTop = true
                        }

                        // fechar menu
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon (
                            Icons.Default.Person,
                            contentDescription = null
                        )
                    }
                )

                // link - configurações
                NavigationDrawerItem(
                    label = { Text("Configurações") },
                    selected = false,
                    onClick = {
                        // selecionar tela a ser exibida
                        navController.navigate("config") {
                            launchSingleTop = true // evita o acumulo na pilha de telas
                            popUpTo (navController.graph.startDestinationId){
                                saveState = true
                            }
                        }

                        // fechar menu
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon (
                            Icons.Default.Settings,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    )  {

        Scaffold (
            // Barra superior
            topBar = {
                TopAppBar(
                    title = { Text("Título")},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    }
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = "start",
                enterTransition = {
                    slideInHorizontally(initialOffsetX = {it}) + fadeIn()
                },
                exitTransition = {
                    slideOutHorizontally(targetOffsetX = {-it}) + fadeOut()
                }
            ) {
                composable("start") {
                    Start()
                }
                composable("profile") {
                    Profile()
                }
                composable("config") {
                    Config()
                }
            }
        }
    }
}

@Composable
fun Start() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Inicio",
            fontSize = 30.sp
        )
    }
}

@Composable
fun Profile() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Perfil",
            fontSize = 30.sp
        )
    }
}

@Composable
fun Config() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Configurações",
            fontSize = 30.sp
        )
    }
}
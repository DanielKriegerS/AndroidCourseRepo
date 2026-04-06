package com.danielks.bottommenunavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.danielks.bottommenunavigation.ui.theme.BottomMenuNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomMenuNavigationTheme {
                NavigationManager()
            }
        }
    }
}

@Composable
fun NavigationManager() {

    // Objeto navController para gerenciar rotas
    val navController = rememberNavController()

    // TopBar, BottomBar e conteúdos (páginas)
    Scaffold(

        // BottomBar - Menu inferior
        bottomBar = {

            // NagigationBar - Componente para englobar os links
            NavigationBar {

                // Link - Início
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("start") },
                    icon = { Icon( Icons.Default.Home, contentDescription = null )}
                )

                // Link - Perfil
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("profile") },
                    icon = { Icon( Icons.Default.Person, contentDescription = null )}
                )

                // Link - Configurações
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("config") },
                    icon = { Icon( Icons.Default.Settings, contentDescription = null )}
                )

            }

        }

    ) {
        innerPadding ->
        // NavHost - Exibir as telas (componentes)
        NavHost(navController = navController, startDestination = "start") {

            // Telas
            composable("start") { Start() }
            composable("profile") { Profile() }
            composable("config") { Config() }

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
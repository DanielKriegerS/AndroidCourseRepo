package com.danielks.crudproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.danielks.crudproject.navigation.Routes
import com.danielks.crudproject.ui.screens.ContactListScreen
import com.danielks.crudproject.ui.screens.ContactFormScreen
import com.danielks.crudproject.viewmodel.CoreViewModel
import com.danielks.crudproject.viewmodel.ZipCodeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            // Mesmas instâncias para as duas telas
            val coreViewModel: CoreViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
            val zipCodeViewModel: ZipCodeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

            NavHost(
                navController = navController,
                startDestination = Routes.CONTACTS
            ) {
                composable(Routes.CONTACTS) {
                    ContactListScreen(
                        navController = navController,
                        coreViewModel = coreViewModel
                    )
                }

                composable(
                    route = "${Routes.CONTACT_FORM}?contactId={contactId}",
                    arguments = listOf(
                        navArgument("contactId") {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ) { backStackEntry ->
                    val contactId = backStackEntry.arguments?.getInt("contactId") ?: -1

                    ContactFormScreen(
                        navController = navController,
                        coreViewModel = coreViewModel,
                        zipCodeViewModel = zipCodeViewModel,
                        contactId = contactId.takeIf { it != -1 }
                    )
                }
            }
        }
    }
}
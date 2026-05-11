package com.danielks.crudproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danielks.crudproject.navigation.Routes
import com.danielks.crudproject.viewmodel.CoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    navController: NavController,
    coreViewModel: CoreViewModel
) {
    val contacts = coreViewModel.contacts
    val loading = coreViewModel.isSubmitting

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Contatos") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routes.CONTACT_FORM) }
            ) { Text("+") }
        }
    ) { padding ->
        Box(Modifier.padding(padding).fillMaxSize()) {

            if (loading) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items (contacts) { contact ->
                    Card {
                        Column(Modifier.padding(16.dp)) {
                            Text(contact.name, style = MaterialTheme.typography.titleMedium)
                            Text(contact.email, style = MaterialTheme.typography.bodyMedium)

                            Spacer(Modifier.height(8.dp))

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(onClick = {
                                    navController.navigate("${Routes.CONTACT_FORM}?contactId=${contact.id}")
                                }) {
                                    Text("Detalhes")
                                }

                                OutlinedButton(onClick = {
                                    coreViewModel.deleteContact(contact.id)
                                }) {
                                    Text("Excluir")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
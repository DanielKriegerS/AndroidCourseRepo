package com.danielks.crudproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danielks.crudproject.model.ZipCode
import com.danielks.crudproject.model.coremodels.Contact
import com.danielks.crudproject.viewmodel.CoreViewModel
import com.danielks.crudproject.viewmodel.ZipCodeViewModel
import com.danielks.crudproject.ui.components.BirthdayPickerField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactFormScreen(
    navController: NavController,
    coreViewModel: CoreViewModel,
    zipCodeViewModel: ZipCodeViewModel,
    contactId: Int?
) {
    // Modo edição sob demanda:
    var isEditMode by rememberSaveable(contactId) {
        mutableStateOf(contactId == null)
    }

    // CEP digitado na UI
    var zipText by rememberSaveable { mutableStateOf("") }

    val zipLoading = zipCodeViewModel.isSubmitting
    val addressFromZip = zipCodeViewModel.addressState

    LaunchedEffect(coreViewModel.saveSucceeded) {
        if (coreViewModel.saveSucceeded) {
            coreViewModel.consumeSaveSucceeded()
            navController.popBackStack()
        }
    }

    // Carrega contato quando entrar na tela com ID
    LaunchedEffect(contactId) {
        if (contactId != null) {
            coreViewModel.getContact(contactId)
        } else {
            coreViewModel.resetFields()  // se você expuser isso
        }
    }

    // Sempre que o Zip retornar address, joga para o Core
    LaunchedEffect(addressFromZip) {
        coreViewModel.updateAddress(addressFromZip)
    }

    // Helper: mantém só dígitos e faz busca quando tiver 8
    fun onZipChanged(text: String) {
        val digits = text.filter { it.isDigit() }
        zipText = digits

        // busca automática ao completar 8 dígitos
        if (digits.length == 8) {
            zipCodeViewModel.getAddressByZipCode(ZipCode(digits)) // ajuste conforme sua classe ZipCode
        } else {
            // opcional: se mudar o CEP, limpar address atual
            coreViewModel.clearAddress()
        }
    }

    val address = coreViewModel.address

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (contactId == null) "Novo contato" else "Contato") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("<")
                    }
                },
                actions = {
                    if (contactId != null) {
                        TextButton(onClick = { isEditMode = !isEditMode }) {
                            Text(if (isEditMode) "Bloquear" else "Editar")
                        }
                    }
                }
            )
        },
        bottomBar = {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {
                        if (contactId == null) {
                            coreViewModel.addContact()
                        } else {
                            // Cria Contact a partir dos campos
                            val updated = Contact(
                                id = contactId,
                                name = coreViewModel.name,
                                email = coreViewModel.email,
                                phone = coreViewModel.phone,
                                birthday = coreViewModel.birthday,
                                address = coreViewModel.address // ideal ser opcional
                            )
                            coreViewModel.updateContact(contactId)
                            isEditMode = false
                        }
                    },
                    enabled = isEditMode && !coreViewModel.isSubmitting,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (contactId == null) "Salvar" else "Atualizar")
                }
            }
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Campos principais
            OutlinedTextField(
                value = coreViewModel.name,
                onValueChange = { coreViewModel.name = it },
                label = { Text("Nome") },
                enabled = isEditMode,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = coreViewModel.email,
                onValueChange = { coreViewModel.email = it },
                label = { Text("Email") },
                enabled = isEditMode,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = coreViewModel.phone,
                onValueChange = { coreViewModel.phone = it },
                label = { Text("Telefone") },
                enabled = isEditMode,
                modifier = Modifier.fillMaxWidth()
            )

            BirthdayPickerField(
                birthdayIso = coreViewModel.birthday,
                enabled = isEditMode,
                onBirthdaySelected = { coreViewModel.birthday = it },
                modifier = Modifier.fillMaxWidth()
            )

            Divider()

            Text("Endereço (opcional)", style = MaterialTheme.typography.titleMedium)

            // CEP
            OutlinedTextField(
                value = zipText,
                onValueChange = { if (isEditMode) onZipChanged(it) },
                label = { Text("CEP") },
                enabled = isEditMode,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    if (zipLoading) {
                        CircularProgressIndicator(Modifier.size(18.dp), strokeWidth = 2.dp)
                    }
                }
            )

            // Campos preenchidos automaticamente (somente leitura)
            OutlinedTextField(
                value = address?.street.orEmpty(),
                onValueChange = { },
                label = { Text("Rua") },
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = address?.neighborhood.orEmpty(),
                onValueChange = { },
                label = { Text("Bairro") },
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = address?.city.orEmpty(),
                    onValueChange = { },
                    label = { Text("Cidade") },
                    enabled = false,
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = address?.state.orEmpty(),
                    onValueChange = { },
                    label = { Text("UF") },
                    enabled = false,
                    modifier = Modifier.width(90.dp)
                )
            }

            // Número: editável
            OutlinedTextField(
                value = address?.number.orEmpty(),
                onValueChange = { newNumber ->
                    if (!isEditMode) return@OutlinedTextField
                    // Atualiza number no address
                    coreViewModel.updateAddress(addressFromZip)
                },
                label = { Text("Número") },
                enabled = isEditMode && address != null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            // Ações
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Voltar")
                }
            }
        }
}
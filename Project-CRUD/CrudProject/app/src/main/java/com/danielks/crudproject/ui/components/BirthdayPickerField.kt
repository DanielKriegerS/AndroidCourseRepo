package com.danielks.crudproject.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayPickerField(
    birthdayIso: String,
    enabled: Boolean,
    onBirthdaySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    // Estado do DatePicker (seleção em millis)
    val datePickerState = rememberDatePickerState()

    val isoFormatter = remember { DateTimeFormatter.ISO_LOCAL_DATE } // yyyy-MM-dd

    // Campo "readOnly" que abre o dialog
    OutlinedTextField(
        value = birthdayIso,
        onValueChange = { /* readOnly */ },
        readOnly = true,
        enabled = enabled,
        label = { Text("Nascimento") },
        modifier = modifier,
        trailingIcon = {
            IconButton(
                onClick = { if (enabled) showDialog = true },
                enabled = enabled
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Selecionar data"
                )
            }
        }
    )

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val millis = datePickerState.selectedDateMillis
                        if (millis != null) {
                            val localDate = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()

                            onBirthdaySelected(localDate.format(isoFormatter)) // yyyy-MM-dd
                        }
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("Cancelar") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
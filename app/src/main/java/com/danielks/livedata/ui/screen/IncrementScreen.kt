package com.danielks.livedata.ui.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import com.danielks.livedata.model.IncrementModel
import com.danielks.livedata.viewmodel.IncrementViewModel

@Composable
fun IncrementScreen(viewModel: IncrementViewModel) {
    val objViewModel by viewModel.obj.observeAsState(IncrementModel())

    Text(
        "Contador :  ${objViewModel.counter}"
    )

    Button(
        onClick = {viewModel.incrementCounter()}
    ) {
        Text("Incrementar 1")
    }

}
package com.danielks.interfaceg

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// AULA 12

@Composable
fun Button(
    text : String,
    function : () -> Unit
)
{
    Button(
        onClick = function,
        colors = ButtonDefaults
            .buttonColors(
                contentColor = Color.Black,
                containerColor = Color.White
            )
    ) {
        Text(text)
    }

}

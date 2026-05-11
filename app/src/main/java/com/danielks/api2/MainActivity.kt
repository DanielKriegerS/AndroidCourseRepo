package com.danielks.api2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danielks.api2.data.model.Student
import com.danielks.api2.data.repository.StudentRepository
import com.danielks.api2.ui.theme.Api2Theme

class MainActivity : ComponentActivity() {

    // chamar repo
    val repository = StudentRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Api2Theme {
                StudentScreen(repository)
            }
        }
    }
}

@Composable
fun StudentScreen(repository: StudentRepository) {
    var studends by remember { mutableStateOf<List<Student>>(emptyList()) }

    var name by remember { mutableStateOf("") }
    var grade1 by remember { mutableDoubleStateOf(0.0) }
    var grade2 by remember { mutableDoubleStateOf(0.0) }


}
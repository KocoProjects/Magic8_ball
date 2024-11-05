package com.jessyj.magic8ball.android

import MagicRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val repository = MagicRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Magic8BallScreen(repository)

        }
    }
}

@Composable
fun Magic8BallScreen(repository: MagicRepository) {
    var answer by remember { mutableStateOf("Shake the Ball to get an answer") }

    Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = answer)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                answer = repository.shakeBall()
            }
        ) {
            Text("Shake!")
        }
    }
}

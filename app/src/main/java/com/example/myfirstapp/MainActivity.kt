package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFirstAppTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Demo") }) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    //CounterScreen(modifier = Modifier.padding(innerPadding))
                    NameCardScreen(modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
}

@Composable
fun CounterScreen(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "当前计数：$count",
            style = MaterialTheme.typography.headlineSmall
        )

        Row(modifier = Modifier.padding(top = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { count-- }) {
                Text("-1")
            }
            Button(onClick = { count++ }) {
                Text("+1")
            }
        }

        Button(
            modifier = Modifier.padding(top = 12.dp),
            onClick = { count = 0 }
        ) {
            Text("重置")
        }
    }
}

@Composable
fun NameCardScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("请输入名字") }
        )

        Text(
            text = if (name.isBlank()) "Hello!" else "Hello, $name!",
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            onClick = { name = "" },
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text("清空")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    MyFirstAppTheme {
        //CounterScreen()
        NameCardScreen()
    }
}
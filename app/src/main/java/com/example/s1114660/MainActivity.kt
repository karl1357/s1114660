package com.example.s1114660

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.s1114660.ui.theme.S1114660Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S1114660Theme {

                    Greeting(

                    )
                }
            }
        }
    }
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "First View")
@Composable
private fun Greeting() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Home") }
    var currentView by remember { mutableStateOf("服務總覽") }
    var imageAlpha by remember { mutableStateOf(1f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.maria),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("簡介", color = Color.Blue) },
                            onClick = {
                                selectedOption = "簡介"
                                expanded = false
                            },
                        )
                        DropdownMenuItem(
                            text = { Text("主要機構", color = Color.Red) },
                            onClick = {
                                selectedOption = "主要機構"
                                expanded = false
                            },
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    item {
                        when (selectedOption) {
                            "簡介" -> Text(
                                text = "簡介",
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color.Blue,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            "主要機構" -> Text(
                                text = "主要機構",
                                color = Color.Red,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }
                }

                val imageRes = when (currentView) {
                    "服務總覽" -> R.drawable.service
                    else -> R.drawable.me
                }

                val alpha by animateFloatAsState(
                    targetValue = imageAlpha,
                    animationSpec = tween(durationMillis = 3000)
                )

                LaunchedEffect(currentView) {
                    imageAlpha = 0f
                    delay(3000)
                    imageAlpha = 1f
                }

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .graphicsLayer(alpha = alpha)
                )

                Button(onClick = {
                    currentView = if (currentView == "服務總覽") "作者：資管二B顏愷" else "服務總覽"
                }) {
                    Text(text = if (currentView == "服務總覽") "作者：資管二B顏愷" else "服務總覽")
                }
            }
        }
    )
}



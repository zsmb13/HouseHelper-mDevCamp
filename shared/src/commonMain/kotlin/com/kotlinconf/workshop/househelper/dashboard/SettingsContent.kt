package com.kotlinconf.workshop.househelper.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kotlinconf.workshop.househelper.storage.loadString
import com.kotlinconf.workshop.househelper.storage.saveString

@Composable
fun SettingsContent() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(Modifier.height(64.dp))
        StorageExample()
        Spacer(Modifier.height(64.dp))
        Placeholder()
    }
}

@Composable
private fun StorageExample() {
    var text by remember { mutableStateOf("") }
    var loadedText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(value = text, onValueChange = { text = it })
        Row {
            Button(onClick = {
                saveString("my-key", text)
                println("Stored")
            }) {
                Text("Store text")
            }
            Button(onClick = {
                loadString("my-key").let {
                    println("Restored: $it")
                    loadedText = it
                }
            }) {
                Text("Restore text")
            }
        }
        Text("Restored text: $loadedText")
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun Placeholder() {
    var selected by remember { mutableStateOf(false) }
    val size = if (selected) 300.dp else 150.dp
    val background = if (selected) Color.Yellow else Color.Transparent
    val rotation = if (selected) 15f else 0f

    Column(
        Modifier
            .size(size)
            .rotate(rotation)
            .background(background)
            .toggleable(selected) { selected = !selected },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            modifier = Modifier.size(72.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "This is a placeholder for more settings content",
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

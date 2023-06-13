package com.example.to_m3.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_m3.ui.theme.ToM3Theme

@Composable
fun ToDoItem(
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .padding(vertical = 4.dp)
        .fillMaxWidth(0.9f)
        .clickable { onClick() }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = title)
            }
            Text(text = description)
        }

    }
}

@Preview
@Composable
fun ToDoItemPreview() {
    ToM3Theme() {
        ToDoItem(title = "Do Laundry", description = "Lorem ipsmum jaskdjhksad", onClick = {})
    }

}
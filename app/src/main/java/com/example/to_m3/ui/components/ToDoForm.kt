package com.example.to_m3.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_m3.R
import com.example.to_m3.ui.theme.ToM3Theme

@Composable
fun ToDoForm(modifier: Modifier = Modifier, todoId: String? = null) {
    Column(
        modifier = modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(
                    id = if (todoId != null) R.string.edit_todo else R.string.create_todo
                ),
                style = MaterialTheme.typography.titleLarge
            )
        }
        TextField(
            placeholder = { Text(text = stringResource(id = R.string.title)) },
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(0.95f)
        )
        TextField(
            placeholder = { Text(text = stringResource(id = R.string.category)) },
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(0.95f)
        )
        TextField(
            placeholder = { Text(text = stringResource(id = R.string.description)) },
            value = "",
            onValueChange = {},
            singleLine = false,
            maxLines = 4,
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(0.95f)
        )

        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(0.6f)) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoFormPreview() {
    ToM3Theme() {
        ToDoForm()
    }
}


@Preview(showBackground = true)
@Composable
fun ToDoFormPreviewEdit() {
    ToM3Theme() {
        ToDoForm(todoId = "1")
    }
}
package com.example.to_m3.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.to_m3.R
import com.example.to_m3.data.models.ToDo
import com.example.to_m3.data.models.ToDoFormEvent
import com.example.to_m3.data.models.mockTodos
import com.example.to_m3.ui.theme.ToM3Theme
import com.example.to_m3.viewmodels.ToDoFormViewModel

@Composable
fun ToDoForm(
    modifier: Modifier = Modifier,
    toDoViewModel: ToDoFormViewModel = viewModel(),
    todo: ToDo? = null
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = todo) {
        if (todo != null) {
            toDoViewModel.setupCurrentTodo(todo)
        }
    }

    Column(
        modifier = modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .verticalScroll(scrollState)
            .fillMaxWidth()
            .height(450.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(
                    id = if (todo != null) R.string.edit_todo else R.string.create_todo
                ),
                style = MaterialTheme.typography.titleLarge
            )
        }
        TextField(
            placeholder = { Text(text = stringResource(id = R.string.title)) },
            value = toDoViewModel.state.title,
            onValueChange = { title -> toDoViewModel.onFormChange(ToDoFormEvent.OnTitleEvent(title)) },
            modifier = Modifier.fillMaxWidth(0.95f),

            )
        TextField(
            placeholder = { Text(text = stringResource(id = R.string.category)) },
            value = toDoViewModel.state.category,
            onValueChange = { category ->
                toDoViewModel.onFormChange(
                    ToDoFormEvent.OnCategoryEvent(
                        category
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(0.95f)
        )
        TextField(
            placeholder = {
                Text(
                    text = stringResource(id = R.string.description)
                )
            },
            value = toDoViewModel.state.description,
            onValueChange = { description ->
                toDoViewModel.onFormChange(
                    ToDoFormEvent.OnDescriptionEvent(
                        description
                    )
                )
            },
            singleLine = false,
            maxLines = 4,
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(0.95f)
        )

        Button(
            onClick = { toDoViewModel.onSaveToDo(onError = {}, onSuccess = {}, todo) },
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoFormPreview() {
    ToM3Theme {
        ToDoForm()
    }
}


@Preview(showBackground = true)
@Composable
fun ToDoFormPreviewEdit() {

    ToM3Theme{
        val mockTodo = mockTodos.first()
        ToDoForm(todo = mockTodo)
    }
}
package com.example.to_m3.ui.screens.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.to_m3.data.models.ToDoFormEvent
import com.example.to_m3.data.models.mockTodos
import com.example.to_m3.ui.components.Screen
import com.example.to_m3.ui.components.ToDoForm
import com.example.to_m3.ui.components.ToDoItem
import com.example.to_m3.ui.theme.ToM3Theme
import com.example.to_m3.viewmodels.AppViewModelProvider
import com.example.to_m3.viewmodels.ToDoFormViewModel
import com.example.to_m3.viewmodels.ToDoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onTodoClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    toDoFormViewModel: ToDoFormViewModel = viewModel(factory = AppViewModelProvider.Factory),
    toDoViewModel: ToDoViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Screen(modifier = modifier.padding(vertical = 8.dp)) {
        if (toDoViewModel.state.loading) {
            CircularProgressIndicator()
        } else {
            val scrollState = rememberLazyListState()
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), state = scrollState) {
                items(toDoViewModel.state.todos) { todo ->
                    ToDoItem(
                        title = todo.title,
                        description = todo.description,
                        isCompleted = todo.isCompleted,
                        onClick = { onTodoClick(todo.id) })
                }
            }
        }


        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                onClick = {
                    toDoFormViewModel.onFormChange(
                        ToDoFormEvent.OnOpenModalEvent(!toDoFormViewModel.state.isModalOpen)
                    )
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.BottomEnd)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

        if (toDoFormViewModel.state.isModalOpen) {
            ModalBottomSheet(
                onDismissRequest = {
                    toDoFormViewModel.onFormChange(
                        ToDoFormEvent.OnOpenModalEvent(!toDoFormViewModel.state.isModalOpen)
                    )
                },
                sheetState = bottomSheetState
            ) {
                ToDoForm(toDoViewModel = toDoFormViewModel)
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ToM3Theme() {
        HomeScreen({})
    }

}
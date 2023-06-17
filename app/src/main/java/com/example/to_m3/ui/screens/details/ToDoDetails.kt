package com.example.to_m3.ui.screens.details

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.to_m3.R
import com.example.to_m3.data.models.ToDoEvent
import com.example.to_m3.data.models.ToDoFormEvent
import com.example.to_m3.data.models.mockTodos
import com.example.to_m3.ui.components.DeleteToDoDialog
import com.example.to_m3.ui.components.Screen
import com.example.to_m3.ui.components.ToDoForm
import com.example.to_m3.ui.theme.ToM3Theme
import com.example.to_m3.viewmodels.AppViewModelProvider
import com.example.to_m3.viewmodels.ToDoFormViewModel
import com.example.to_m3.viewmodels.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    onSuccessDelete: () -> Unit,
    modifier: Modifier = Modifier,
    todoId: Int? = null,
    toDoFormViewModel: ToDoFormViewModel = viewModel(factory = AppViewModelProvider.Factory),
    toDoViewModel: ToDoViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val context = LocalContext.current

    LaunchedEffect(key1 = toDoViewModel.state.currentTodo) {
        if (todoId != null) {
            toDoViewModel.getTodoById(todoId)
        }
    }

    Screen(modifier = modifier.padding(top = 8.dp)) {
        if (toDoViewModel.state.loading) {
            CircularProgressIndicator()
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.5f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = toDoViewModel.state.currentTodo?.title ?: "")
                        Text(text = toDoViewModel.state.currentTodo?.category ?: "")
                        Text(text = toDoViewModel.state.currentTodo?.description ?: "")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Switch(
                            checked = toDoViewModel.state.currentTodo?.isCompleted ?: false,
                            onCheckedChange = {
                                toDoViewModel.updateCompletion(
                                    !(toDoViewModel.state.currentTodo?.isCompleted ?: false)
                                )
                            })
                        Text(text = stringResource(id = R.string.is_completed))
                    }

                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Button(
                    onClick = { toDoViewModel.onChangeState(ToDoEvent.OnOpenDeleteDialog(true)) },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(text = stringResource(id = R.string.delete))
                }

            }
        }


        if (toDoFormViewModel.state.isModalOpen) {
            ModalBottomSheet(
                onDismissRequest = {
                    toDoFormViewModel.onFormChange(
                        ToDoFormEvent.OnOpenModalEvent(false)
                    )
                },
                sheetState = bottomSheetState
            ) {
                ToDoForm(todo = toDoViewModel.state.currentTodo, toDoViewModel = toDoFormViewModel)
            }
        }

        if (toDoViewModel.state.showDialog) {
            DeleteToDoDialog(onDismiss = {
                toDoViewModel.onChangeState(
                    ToDoEvent.OnOpenDeleteDialog(
                        false
                    )
                )
            }, onConfirm = {
                toDoViewModel.deleteTodo(
                    onError = {
                        Toast.makeText(
                            context,
                            "We couldn't delete the To-Do please try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }, onSuccess = onSuccessDelete
                )
            })
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ToM3Theme() {
        DetailsScreen({})
    }

}
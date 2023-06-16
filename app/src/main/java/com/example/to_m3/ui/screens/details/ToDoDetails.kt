package com.example.to_m3.ui.screens.details

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.to_m3.R
import com.example.to_m3.data.models.ToDoFormEvent
import com.example.to_m3.data.models.mockTodos
import com.example.to_m3.ui.components.Screen
import com.example.to_m3.ui.components.ToDoForm
import com.example.to_m3.ui.theme.ToM3Theme
import com.example.to_m3.viewmodels.AppViewModelProvider
import com.example.to_m3.viewmodels.ToDoFormViewModel
import com.example.to_m3.viewmodels.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    toDoFormViewModel: ToDoFormViewModel = viewModel(factory = AppViewModelProvider.Factory),
    toDoViewModel: ToDoViewModel = viewModel()
) {

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Screen(modifier = modifier.padding(top = 8.dp)) {
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
                    Text(text = "Title")
                    Text(text = "Category")
                    Text(text = "Description")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Switch(checked = false, onCheckedChange = {})
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
            Button(onClick = { /*TODO*/ }, modifier = Modifier.width(150.dp)) {
                Text(text = stringResource(id = R.string.delete))
            }
            Button(
                onClick = {
                    toDoFormViewModel.onFormChange(
                        ToDoFormEvent.OnOpenModalEvent(!toDoFormViewModel.state.isModalOpen)
                    )
                },
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = stringResource(id = R.string.edit))
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
    }

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ToM3Theme() {
        DetailsScreen()
    }

}
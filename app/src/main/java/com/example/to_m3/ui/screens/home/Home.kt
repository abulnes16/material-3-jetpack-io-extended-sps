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
import com.example.to_m3.data.models.mockTodos
import com.example.to_m3.ui.components.Screen
import com.example.to_m3.ui.components.ToDoForm
import com.example.to_m3.ui.components.ToDoItem
import com.example.to_m3.ui.theme.ToM3Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onTodoClick: (String) -> Unit, modifier: Modifier = Modifier) {
    var openBottomSheet by rememberSaveable() {
        mutableStateOf(false)
    }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Screen(modifier = modifier.padding(vertical = 8.dp)) {
        val scrollState = rememberLazyListState()
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), state = scrollState) {
            items(mockTodos) { todo ->
                ToDoItem(
                    title = todo.title,
                    description = todo.description,
                    onClick = { onTodoClick(todo.id) })
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                onClick = { openBottomSheet = !openBottomSheet },
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.BottomEnd)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

        if (openBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheet = false },
                sheetState = bottomSheetState
            ) {
                ToDoForm()
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
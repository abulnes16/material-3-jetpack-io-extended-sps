package com.example.to_m3.ui.screens.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_m3.R
import com.example.to_m3.data.models.mockTodos
import com.example.to_m3.ui.components.Screen
import com.example.to_m3.ui.components.ToDoItem
import com.example.to_m3.ui.theme.ToM3Theme


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Screen(modifier = modifier.padding(vertical = 8.dp)) {
        val scrollState = rememberLazyListState()
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), state = scrollState) {
            items(mockTodos) { todo ->
                ToDoItem(title = todo.title, description = todo.description, onClick = { /*TODO*/ })
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ToM3Theme() {
        HomeScreen()
    }

}
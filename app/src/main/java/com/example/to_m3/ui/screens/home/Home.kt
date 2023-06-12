package com.example.to_m3.ui.screens.home


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_m3.ui.components.Screen


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Screen(modifier = modifier) {
        Text(text = "This is the home screen")
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
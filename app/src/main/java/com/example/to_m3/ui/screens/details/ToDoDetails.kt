package com.example.to_m3.ui.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_m3.ui.components.Screen

@Composable
fun DetailsScreen(modifier: Modifier = Modifier) {
    Screen(modifier = modifier) {
        Text(text = "This is the detail screen")
    }

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailsScreen()
}
package com.example.to_m3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.to_m3.ui.navigation.ToM3NavHost
import com.example.to_m3.ui.theme.ToM3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToM3App()
        }
    }
}

@Composable
fun ToM3App() {

    ToM3Theme() {
        val navController = rememberNavController()
        ToM3NavHost(navController = navController )
    }
}

@Preview(showBackground = true)
@Composable
fun ToM3AppPreview() {
    ToM3Theme {
       ToM3App()
    }
}
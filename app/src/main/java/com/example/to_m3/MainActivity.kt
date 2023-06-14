package com.example.to_m3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.to_m3.ui.components.Screen
import com.example.to_m3.ui.components.ToM3TopBar
import com.example.to_m3.ui.navigation.Details
import com.example.to_m3.ui.navigation.Home
import com.example.to_m3.ui.navigation.ToM3NavHost
import com.example.to_m3.ui.navigation.destinations
import com.example.to_m3.ui.theme.ToM3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToM3App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToM3App() {

    ToM3Theme() {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val route = currentDestination?.route ?: "home"
        val currentScreen = if (route.startsWith("home")) Home else Details

        Scaffold(
            topBar = {
                ToM3TopBar(
                    currentScreen = currentScreen,
                    onGoBack = { navController.popBackStack() },
                    title = currentScreen.title
                )
            }
        ) { contentPadding ->
            ToM3NavHost(navController = navController, modifier = Modifier.padding(contentPadding))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ToM3AppPreview() {
    ToM3Theme {
        ToM3App()
    }
}
package com.example.to_m3.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_m3.R
import com.example.to_m3.ui.navigation.Details
import com.example.to_m3.ui.navigation.Home
import com.example.to_m3.ui.navigation.ToM3Destinations
import com.example.to_m3.ui.theme.ToM3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToM3TopBar(
    currentScreen: ToM3Destinations,
    onGoBack: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title ?: stringResource(id = R.string.app_name)) },
        navigationIcon = {
            if (currentScreen === Details) {
                IconButton(onClick = onGoBack) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}


@Preview(showBackground = true)
@Composable
fun ToM3TopBarPreview() {
    ToM3Theme() {
        ToM3TopBar(currentScreen = Home, onGoBack = { /*TODO*/ })
    }

}

@Preview(showBackground = true)
@Composable
fun ToM3TopBarDetailsPreview() {
    ToM3Theme() {
        ToM3TopBar(currentScreen = Details, onGoBack = { /*TODO*/ })
    }

}
package com.example.compose_magazin.presentation.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun MainScaffold(
    navController: NavHostController,
    scaffoldViewModel: ScaffoldViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            NavigationBar(
                navController = navController,
                scaffoldViewModel = scaffoldViewModel
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationComponent(
                navController = navController,
                scaffoldViewModel = scaffoldViewModel
            )
        }
    }
}

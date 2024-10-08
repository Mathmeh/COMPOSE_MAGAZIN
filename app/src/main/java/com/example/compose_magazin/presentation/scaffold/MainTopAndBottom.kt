package com.example.compose_magazin.presentation.scaffold

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_magazin.presentation.cart.CartScreen
import com.example.compose_magazin.presentation.catalog.CatalogScreen
import com.example.compose_magazin.presentation.settings.SettingsScreen
import com.example.compose_magazin.presentation.components.CartBadgedBox

@Composable
fun NavigationComponent(
    navController: NavHostController,
    scaffoldViewModel: ScaffoldViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = "catalog",
        route = "parent"
    ) {
        composable("catalog") { backStackEntry ->

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("parent")
            }

            CatalogScreen(
                navController = navController,
                scaffoldViewModel = scaffoldViewModel,
                catalogScreenViewModel = hiltViewModel(parentEntry),
                productCardsViewModel = hiltViewModel(parentEntry)
            )
        }
        composable("cart") { backStackEntry ->

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("parent")
            }

            CartScreen(
                navController = navController,
                scaffoldViewModel = scaffoldViewModel,
                catalogScreenViewModel = hiltViewModel(parentEntry),
                productCardsViewModel = hiltViewModel(parentEntry)
            )
        }
        composable("settings") { SettingsScreen(navController = navController) }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavigationBar(
    navController: NavHostController,
    scaffoldViewModel: ScaffoldViewModel
) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        NavigationBarItemData("Catalog", "catalog", Icons.Default.Menu),
        NavigationBarItemData("Cart", "cart", Icons.Default.ShoppingCart),
        NavigationBarItemData("Settings", "settings", Icons.Default.Settings)
    )

    androidx.compose.material3.NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    if (item.label == "Cart" && scaffoldViewModel.cartItemCount.value > 0) {
                        CartBadgedBox(
                            text = scaffoldViewModel.cartItemCount.value.toString(),
                            icon = item.icon,
                            label = item.label
                        )
                    } else {
                        Icon(item.icon, contentDescription = item.label)
                    }
                },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Store") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EE), titleContentColor = Color.White
        )
    )
}

data class NavigationBarItemData(val label: String, val route: String, val icon: ImageVector)

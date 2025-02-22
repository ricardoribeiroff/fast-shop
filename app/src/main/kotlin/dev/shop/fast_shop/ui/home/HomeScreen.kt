package dev.shop.fast_shop.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.shop.fast_shop.ui.theme.FastShopTheme




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    FastShopTheme(darkTheme = viewModel.isDarkMode) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Home, contentDescription = "Home")
                        }
                        IconButton(onClick = {
                            viewModel.toggleDarkMode()
                        }) {
                            AnimatedContent(
                                targetState = viewModel.state.icon,
                                transitionSpec = {
                                    fadeIn(animationSpec = tween(500)
                                    ) togetherWith fadeOut(animationSpec = tween(500))
                                }
                            ) { targetIcon ->
                                Icon(targetIcon, contentDescription = "Dark/Light Mode")
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { /* do something */ },
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, "Localized description")
                        }
                    }
                ) }
        ) {innerPadding ->
            Text(
                modifier = Modifier.padding(innerPadding),
                text = "HOME / DASHBOARD"
            )
        }
        Column {
            HorizontalDivider()
            ListItem(
                headlineContent = { Text("Compras do mês") },
                overlineContent = { Text("18/02/2025") },
                supportingContent = { Text("Supra") },
                leadingContent = {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = "Localized description",
                        modifier = Modifier.padding(top = 14.dp)
                    )
                },

                trailingContent = { Text("meta") }
            )
            HorizontalDivider()
        }

    }


}
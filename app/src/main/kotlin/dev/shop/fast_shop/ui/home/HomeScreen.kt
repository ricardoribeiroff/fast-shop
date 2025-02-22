package dev.shop.fast_shop.ui.home

import android.annotation.SuppressLint
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
import dev.shop.fast_shop.ui.login.LoginViewModel
import dev.shop.fast_shop.ui.theme.FastShopTheme




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel()
) {
    FastShopTheme(darkTheme = homeViewModel.isDarkMode) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("fast shop")
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Home, contentDescription = "Home")
                        }
                        IconButton(onClick = {
                            homeViewModel.toggleDarkMode()
                        }) {
                            AnimatedContent(
                                targetState = homeViewModel.state.icon,
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
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                HorizontalDivider()
                homeViewModel.products.forEach { product ->
                    ListItem(
                        headlineContent = { Text(product.name) },
                        overlineContent = { Text(product.date.toString()) },
                        supportingContent = { Text(product.market) },
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
    }
}
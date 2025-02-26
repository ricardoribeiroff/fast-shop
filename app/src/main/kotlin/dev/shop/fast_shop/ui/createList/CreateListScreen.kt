package dev.shop.fast_shop.ui.createList

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.shop.fast_shop.data.DatabaseHelper
import dev.shop.fast_shop.ui.component.DateVisualTransformation
import dev.shop.fast_shop.ui.home.CreateListViewModel
import dev.shop.fast_shop.ui.home.HomeViewModel
import dev.shop.fast_shop.ui.theme.FastShopTheme
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.google.firebase.Timestamp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateListScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(),
    createListViewModel: CreateListViewModel = viewModel(),
) {
    val auth = createListViewModel.auth
    val state = createListViewModel.state
    val dbHelper = DatabaseHelper()
    FastShopTheme(darkTheme = homeViewModel.isDarkMode) {
        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "fast \nshop",
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        modifier = Modifier.padding(top = 140.dp),
                        text = "Criar Lista",
                        style = MaterialTheme.typography.displaySmall,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
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
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                            onClick = {
                                val list = createListViewModel.state.copy(
                                    id ="",
                                    name = state.name,
                                    market = state.market,
                                    date = dbHelper.convertStringToTimestamp(createListViewModel.rawDate)?.toDate(),
                                    uidUser = auth.currentUser!!.uid
                                )
                                createListViewModel.viewModelScope.launch {
                                    dbHelper.addList(list)
                                }
                                navController.navigate("HomeScreen")
                            },
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, "Localized description")
                        }
                    }
                ) }
        ) { innerPadding ->
            Column(modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)) {
                Text(
                    text = "Nome",
                    style = MaterialTheme.typography.titleLarge)
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    value = state.name,
                    onValueChange = { createListViewModel.onNameChange(it)},
                )
                Text(
                    text = "Estabelecimento",
                    style = MaterialTheme.typography.titleLarge)
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    value = state.market,
                    onValueChange = { createListViewModel.onMarketChange(it)},
                )
                Text(
                    text = "Data",
                    style = MaterialTheme.typography.titleLarge)
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    value = createListViewModel.rawDate,
                    onValueChange = { date ->
                        createListViewModel.onDateChange(date)
                    },
                    visualTransformation = DateVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    placeholder = { Text("dd/mm/yy") },
                )
            }
        }
    }
}


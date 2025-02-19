package dev.shop.fast_shop.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Home, contentDescription = "teste")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
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
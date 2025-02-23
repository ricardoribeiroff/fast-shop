package dev.shop.fast_shop.ui.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composables.icons.lucide.*
import com.google.firebase.auth.FirebaseAuth
import dev.shop.fast_shop.data.DatabaseHelper
import dev.shop.fast_shop.model.HomeState
import dev.shop.fast_shop.model.Products
import kotlinx.coroutines.launch
import dev.shop.fast_shop.service.AuthService

class HomeViewModel(
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    var isDarkMode by mutableStateOf(false)
        private set

    var products by mutableStateOf(listOf<Products>())
        private set

    private val databaseHelper = DatabaseHelper()

    var uidUser = auth.currentUser?.uid

    init {
        fetchProducts(uidUser.toString())
    }


    fun toggleDarkMode() {
        isDarkMode = !isDarkMode
        state = state.copy(
            icon = if (isDarkMode) Lucide.Sun else Lucide.Moon
        )
    }

    fun fetchProducts(uidUser: String) {
        viewModelScope.launch {
            products = databaseHelper.getProducts(uidUser)
        }
    }



}
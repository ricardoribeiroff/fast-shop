package dev.shop.fast_shop.ui.home

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.composables.icons.lucide.*
import dev.shop.fast_shop.model.HomeState



class HomeViewModel() : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set



    var isDarkMode = false
        private set

    fun toggleDarkMode(){
        isDarkMode = !isDarkMode
        state = state.copy(
            icon = if (isDarkMode) Lucide.Sun else Lucide.Moon
        )
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

    }



}
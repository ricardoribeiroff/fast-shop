package dev.shop.fast_shop.ui.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dev.shop.fast_shop.data.DatabaseHelper
import dev.shop.fast_shop.model.Lists

class CreateListViewModel(
    val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    val dbHelper: DatabaseHelper = DatabaseHelper()
) : ViewModel() {

    var state by mutableStateOf(Lists())
        private set

    var rawDate by mutableStateOf("")
        private set

    val uidUser = auth.currentUser!!.uid


    fun onNameChange(name: String) {
        state = state.copy(name = name)
    }
    fun onDateChange(date: String) {
        rawDate = date.filter { it.isDigit() }.take(8)
        state = state.copy(date = dbHelper.convertStringToTimestamp(rawDate)?.toDate())
    }
    fun onMarketChange(market: String) {
        state = state.copy(market = market)
    }
    fun captureUidUser(uidUser: String) {
        state = state.copy(uidUser = uidUser)
    }


}
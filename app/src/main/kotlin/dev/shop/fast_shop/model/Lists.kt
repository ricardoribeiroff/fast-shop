package dev.shop.fast_shop.model

import java.util.Date

data class Lists(
    val id: String = "",
    val name: String = "",
    val date: Date? = null,
    val market: String = "",
    val uidUser: String = ""
)
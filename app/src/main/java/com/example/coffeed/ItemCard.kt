package com.example.coffeed

import android.net.Uri

data class ItemCard(
    val uid: Int = 1,
    val coffeePhoto: String = "android.resource://com.example.coffeed/drawable/coffee_photo",
    val name: String = "name",
    val manufacturer: String = "",
    val type: String = "",
    val rating: Int = 3,
    val shortDescription: String = "",
    val longDescription: String = ""
)

data class PreviewItemCard(
    val coffeePhoto: String = "android.resource://com.example.coffeed/drawable/coffee_photo",
    val name: String = "name",
    val manufacturer: String = "",
    val rating: Int = 3,
    val uid: Int = 1
)


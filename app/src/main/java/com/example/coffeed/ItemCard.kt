package com.example.coffeed

import android.net.Uri

data class ItemCard(
    val coffeePhoto: Uri = Uri.parse("android.resource://com.example.coffeed/drawable/coffee_photo"),
    val name: String = "name",
    val manufacturer: String = "",
    val type: String = "",
    val rating: Int = 3,
    val shortDescription: String = "",
    val longDescription: String = ""
)


package com.example.coffeed

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemCard(
    val uid: Int = 1,
    val coffeePhoto: String = "android.resource://com.example.coffeed/drawable/coffee_photo",
    val name: String = "name",
    val manufacturer: String = "",
    val type: String = "",
    val rating: Float = 3.0F,
    val shortDescription: String = "",
    val longDescription: String = "",
): Parcelable

data class PreviewItemCard(
    val coffeePhoto: String = "android.resource://com.example.coffeed/drawable/coffee_photo",
    val name: String = "name",
    val manufacturer: String = "",
    val type: String = "",
    val rating: Float = 3.0F,
    val uid: Int = 1
)


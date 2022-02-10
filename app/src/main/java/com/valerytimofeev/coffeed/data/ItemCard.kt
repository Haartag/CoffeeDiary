package com.valerytimofeev.coffeed.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemCard(
    val uid: Int = 1,
    val coffeePhoto: String = "android.resource://com.valerytimofeev.coffeed/drawable/coffee_photo",
    val name: String = "name",
    val manufacturer: String = "",
    val type: String = "",
    val rating: Float = 3.0F,
    val description: String = ""
): Parcelable

data class PreviewItemCard(
    val coffeePhoto: String = "android.resource://com.valerytimofeev.coffeed/drawable/coffee_photo",
    val name: String = "name",
    val manufacturer: String = "",
    val type: String = "",
    val rating: Float = 3.0F,
    val uid: Int = 1
)

data class DetailsItemCard(
    val uid: Int,
    val coffeeName: String = "name",
    val detailedPhoto: String = "",
    val detailedText: String = "",
    val coffeeId: Int = 1
)


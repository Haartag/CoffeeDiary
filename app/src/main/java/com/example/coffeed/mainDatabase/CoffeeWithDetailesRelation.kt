package com.example.coffeed.mainDatabase

import androidx.room.Embedded
import androidx.room.Relation

data class CoffeeWithDetails(
    @Embedded val coffeeItem: CoffeeItem,
    @Relation(
        parentColumn = "uid",
        entityColumn = "coffeeId"
    )
    val detailedItems: List<DetailedItem>
)
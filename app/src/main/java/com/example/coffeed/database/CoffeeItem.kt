package com.example.coffeed.database

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coffeeDB")
data class CoffeeItem(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo val coffeePhoto: String,
    @ColumnInfo val name: String,
    @ColumnInfo val manufacturer: String,
    @ColumnInfo val type: String,
    @ColumnInfo val rating: Float,
    @ColumnInfo val shortDescription: String,
    @ColumnInfo val longDescription: String,

)

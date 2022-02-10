package com.valerytimofeev.coffeed.mainDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "detailedDB")
data class DetailedItem(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo val detailedPhoto: String,
    @ColumnInfo val detailedText: String,
    @ColumnInfo val coffeeId: Int
)
package com.example.coffeed.mainDatabase

import androidx.room.*
import com.example.coffeed.data.ItemCard
import com.example.coffeed.data.PreviewItemCard


@Dao
interface CoffeeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(item: CoffeeItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(item: DetailedItem)

    @Update
    suspend fun update(item: CoffeeItem)

    @Update
    suspend fun update(item: DetailedItem)

    @Delete
    suspend fun delete(item: CoffeeItem)

    @Delete
    suspend fun delete(item: DetailedItem)

    @Query("SELECT * FROM coffeeDB")
    suspend fun getAll(): Array<CoffeeItem>

    @Query("SELECT coffeePhoto, name, manufacturer, rating, type, uid FROM coffeeDB")
    suspend fun getAllPreviewItems(): Array<PreviewItemCard>

    @Query("SELECT COUNT(*) FROM coffeeDB")
    suspend fun countType(): Int

    @Query("SELECT * FROM coffeeDB WHERE uid = :uid")
    suspend fun getAllById(uid: Int): CoffeeItem

    @Query("SELECT coffeePhoto, name, manufacturer, rating, type, uid FROM coffeeDB WHERE uid = :uid")
    suspend fun getPreviewItemById(uid: Int): PreviewItemCard

    @Query("SELECT * FROM coffeeDB WHERE uid = :uid")
    suspend fun getItemById(uid: Int): ItemCard

    @Query("DELETE FROM coffeeDB WHERE uid = :uid")
    suspend fun deleteItemById(uid: Int)

    @Transaction
    @Query("SELECT * FROM coffeeDB WHERE uid = :uid")
    suspend fun getDetailedItems(uid: Int): List<CoffeeWithDetails>

    @Query("SELECT name FROM coffeeDB WHERE uid = :uid")
    suspend fun getNameById(uid: Int): String
}
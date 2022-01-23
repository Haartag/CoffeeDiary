package com.example.coffeed.database

import androidx.room.*
import com.example.coffeed.ItemCard
import com.example.coffeed.PreviewItemCard
import com.example.coffeed.RecyclerViewItem


@Dao
interface CoffeeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: CoffeeItem)

    @Update
    fun update(item: CoffeeItem)

    @Delete
    fun delete(item: CoffeeItem)

    @Query("SELECT * FROM coffeeDB")
    fun getAll(): Array<CoffeeItem>

    @Query("SELECT coffeePhoto, name, manufacturer, rating, uid FROM coffeeDB")
    fun getAllPreviewItems(): Array<PreviewItemCard>

    @Query("SELECT COUNT(*) FROM coffeeDB")
    fun countType(): Int

    @Query("SELECT * FROM coffeeDB WHERE uid = :uid")
    fun getAllById(uid: Int): CoffeeItem

    @Query("SELECT coffeePhoto, name, manufacturer, rating, uid FROM coffeeDB WHERE uid = :uid")
    fun getPreviewItemById(uid: Int): PreviewItemCard

    @Query("SELECT * FROM coffeeDB WHERE uid = :uid")
    fun getItemById(uid: Int): ItemCard

    @Query("DELETE FROM coffeeDB WHERE uid = :uid")
    fun deleteItemById(uid: Int)
}
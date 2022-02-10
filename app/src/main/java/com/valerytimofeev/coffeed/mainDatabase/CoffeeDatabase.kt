package com.valerytimofeev.coffeed.mainDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [CoffeeItem::class, DetailedItem::class], version = 1, exportSchema = false
)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract val coffeeDao: CoffeeDAO

    companion object {
        @Volatile
        private var INSTANCE: CoffeeDatabase? = null

        fun getInstance(context: Context): CoffeeDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CoffeeDatabase::class.java,
                        "CoffeeDB"
                    )

                        //.allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
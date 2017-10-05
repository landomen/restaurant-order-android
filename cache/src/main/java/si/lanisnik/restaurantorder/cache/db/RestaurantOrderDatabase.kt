package si.lanisnik.restaurantorder.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import si.lanisnik.restaurantorder.cache.dao.CacheValidityDao
import si.lanisnik.restaurantorder.cache.dao.CachedFoodCategoryDao
import si.lanisnik.restaurantorder.cache.dao.CachedMenuItemDao
import si.lanisnik.restaurantorder.cache.db.converters.RoomTypeConverters
import si.lanisnik.restaurantorder.cache.model.foodcategory.CachedFoodCategory
import si.lanisnik.restaurantorder.cache.model.menuitem.CachedMenuItem
import si.lanisnik.restaurantorder.cache.model.validity.CacheValidity
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 20/09/2017.
 * domen.lanisnik@gmail.com
 */
@Database(entities = arrayOf(CachedMenuItem::class, CachedFoodCategory::class, CacheValidity::class), version = 1)
@TypeConverters(RoomTypeConverters::class)
abstract class RestaurantOrderDatabase @Inject constructor() : RoomDatabase() {

    abstract fun foodCategoryDao(): CachedFoodCategoryDao
    abstract fun menuItemDao(): CachedMenuItemDao
    abstract fun validityDao(): CacheValidityDao

    companion object {
        @Volatile private var INSTANCE: RestaurantOrderDatabase? = null

        fun getInstance(context: Context): RestaurantOrderDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context): RestaurantOrderDatabase =
                Room.databaseBuilder(context.applicationContext,
                        RestaurantOrderDatabase::class.java, "RestaurantOrder.db")
                        .build()
    }

}

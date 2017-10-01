package si.lanisnik.restaurantorder.data.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import si.lanisnik.restaurantorder.data.cache.dao.MenuItemsDao
import si.lanisnik.restaurantorder.data.cache.model.foodcategory.CachedFoodCategory
import si.lanisnik.restaurantorder.data.cache.model.menuitem.CachedMenuItem
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 20/09/2017.
 * domen.lanisnik@gmail.com
 */
@Database(entities = arrayOf(CachedMenuItem::class, CachedFoodCategory::class), version = 1)
abstract class RestaurantOrderDatabase @Inject constructor() : RoomDatabase() {

    abstract fun menuItemsDao(): MenuItemsDao

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

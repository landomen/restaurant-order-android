package si.lanisnik.restaurantorder.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import si.lanisnik.restaurantorder.data.local.dao.MenuItemsDao
import si.lanisnik.restaurantorder.data.local.entity.menuitem.MenuItemEntity

/**
 * Created by Domen Lanišnik on 20/09/2017.
 * domen.lanisnik@gmail.com
 */
@Database(entities = arrayOf(MenuItemEntity::class), version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun menuItemsDao(): MenuItemsDao

    companion object {

        @Volatile private var INSTANCE: si.lanisnik.restaurantorder.data.local.LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context): LocalDatabase =
                Room.databaseBuilder(context.applicationContext,
                        LocalDatabase::class.java, "RestaurantOrder.db")
                        .build()
    }
}
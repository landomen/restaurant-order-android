package si.lanisnik.restaurantorder.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import si.lanisnik.restaurantorder.cache.db.constants.DatabaseConstants
import si.lanisnik.restaurantorder.cache.model.menuitem.CachedMenuItem

@Dao
interface CachedMenuItemDao {

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_MENU_ITEM} WHERE id = :categoryId")
    fun getAllMenuItems(categoryId: Int): List<CachedMenuItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenuItem(cachedMenuItem: CachedMenuItem)

    @Query("DELETE FROM ${DatabaseConstants.TABLE_MENU_ITEM} WHERE categoryId = :categoryId")
    fun clearMenuItems(categoryId: Int)
}
package si.lanisnik.restaurantorder.data.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import si.lanisnik.restaurantorder.data.cache.db.constants.DatabaseConstants
import si.lanisnik.restaurantorder.data.cache.model.menuitem.CachedMenuItem

/**
 * Created by Domen Lanišnik on 20/09/2017.
 * domen.lanisnik@gmail.com
 */
@Dao
interface MenuItemsDao {

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_MENU_ITEM} WHERE id = :categoryId")
    fun getAllMenuItems(categoryId: Int): List<CachedMenuItem>
}
package si.lanisnik.restaurantorder.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.local.entity.menuitem.MenuItemEntity

/**
 * Created by Domen Lanišnik on 20/09/2017.
 * domen.lanisnik@gmail.com
 */
@Dao
interface MenuItemsDao {

    @Query("SELECT * FROM menu_items WHERE id = :categoryId")
    fun getAllMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>>
}
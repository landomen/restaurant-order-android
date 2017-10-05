package si.lanisnik.restaurantorder.cache.model.menuitem

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import si.lanisnik.restaurantorder.cache.db.constants.DatabaseConstants
import si.lanisnik.restaurantorder.cache.model.foodcategory.CachedFoodCategory

/**
 * Created by Domen Lanišnik on 24/09/2017.
 * domen.lanisnik@gmail.com
 */
@Entity(tableName = DatabaseConstants.TABLE_MENU_ITEM,
        foreignKeys = arrayOf(ForeignKey(entity = CachedFoodCategory::class, parentColumns = arrayOf("id"), childColumns = arrayOf("categoryId"))))
data class CachedMenuItem(
        @PrimaryKey
        var id: Int,
        var title: String,
        var description: String,
        var image: String?,
        var price: Double,
        var categoryId: Int
)
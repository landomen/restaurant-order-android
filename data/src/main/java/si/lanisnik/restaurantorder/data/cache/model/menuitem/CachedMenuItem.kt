package si.lanisnik.restaurantorder.data.cache.model.menuitem

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import si.lanisnik.restaurantorder.data.cache.db.constants.DatabaseConstants

/**
 * Created by Domen Lanišnik on 24/09/2017.
 * domen.lanisnik@gmail.com
 */
@Entity(tableName = DatabaseConstants.TABLE_MENU_ITEM)
data class CachedMenuItem(
        @PrimaryKey
        var id: Int,
        var title: String,
        var description: String,
        var image: String,
        var price: Double,
        var category: String
)
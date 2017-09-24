package si.lanisnik.restaurantorder.data.persistence.entity.menuitem

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Domen Lanišnik on 24/09/2017.
 * domen.lanisnik@gmail.com
 */
@Entity(tableName = "menu_items")
data class MenuItemEntity(
        @PrimaryKey
        val id: Int = 0,
        val title: String,
        val description: String,
        val image: String? = null,
        val price: Double,
        val category: String
)
package si.lanisnik.restaurantorder.data.entity.menuitems

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
@Entity(tableName = "food_category")
data class FoodCategory(
        @PrimaryKey
        val id: Int = 0,
        val title: String = ""
)
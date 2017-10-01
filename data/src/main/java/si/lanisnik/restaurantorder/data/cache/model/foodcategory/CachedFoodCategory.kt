package si.lanisnik.restaurantorder.data.cache.model.foodcategory

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import si.lanisnik.restaurantorder.data.cache.db.constants.DatabaseConstants

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
@Entity(tableName = DatabaseConstants.TABLE_FOOD_CATEGORY)
data class CachedFoodCategory(
        @PrimaryKey
        var id: Int,
        var title: String
)
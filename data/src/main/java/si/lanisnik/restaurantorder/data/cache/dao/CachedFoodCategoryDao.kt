package si.lanisnik.restaurantorder.data.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import si.lanisnik.restaurantorder.data.cache.db.constants.DatabaseConstants
import si.lanisnik.restaurantorder.data.cache.model.foodcategory.CachedFoodCategory

@Dao
interface CachedFoodCategoryDao {

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_FOOD_CATEGORY}")
    fun getCategories(): List<CachedFoodCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(cachedFoodCategory: CachedFoodCategory)

    @Query("DELETE FROM ${DatabaseConstants.TABLE_FOOD_CATEGORY}")
    fun clearCategories()

}
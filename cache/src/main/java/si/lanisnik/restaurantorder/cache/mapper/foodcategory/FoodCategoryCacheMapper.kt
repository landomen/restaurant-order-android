package si.lanisnik.restaurantorder.cache.mapper.foodcategory

import si.lanisnik.restaurantorder.cache.mapper.CacheEntityMapper
import si.lanisnik.restaurantorder.cache.model.foodcategory.CachedFoodCategory
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity
import javax.inject.Inject

/**
 * Defines mapping operations between cache and data layer for [FoodCategoryEntity].
 */
class FoodCategoryCacheMapper @Inject constructor() : CacheEntityMapper<CachedFoodCategory, FoodCategoryEntity> {

    override fun mapFromCached(cachedModel: CachedFoodCategory): FoodCategoryEntity =
            FoodCategoryEntity(cachedModel.id, cachedModel.title)

    override fun mapToCached(dataModel: FoodCategoryEntity): CachedFoodCategory =
            CachedFoodCategory(dataModel.id, dataModel.title)
}
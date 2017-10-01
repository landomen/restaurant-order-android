package si.lanisnik.restaurantorder.data.cache.mapper

import si.lanisnik.restaurantorder.data.cache.model.foodcategory.CachedFoodCategory
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity

/**
 * Created by Domen Lanišnik on 30/09/2017.
 * domen.lanisnik@gmail.com
 */
class FoodCategoryCacheMapper : CacheEntityMapper<CachedFoodCategory, FoodCategoryEntity> {

    override fun mapFromCached(cachedModel: CachedFoodCategory): FoodCategoryEntity =
            FoodCategoryEntity(cachedModel.id, cachedModel.title)

    override fun mapToCached(dataModel: FoodCategoryEntity): CachedFoodCategory =
            CachedFoodCategory(dataModel.id, dataModel.title)
}
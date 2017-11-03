package si.lanisnik.restaurantorder.cache.mapper.menuitem

import si.lanisnik.restaurantorder.cache.mapper.CacheEntityMapper
import si.lanisnik.restaurantorder.cache.mapper.foodcategory.FoodCategoryCacheMapper
import si.lanisnik.restaurantorder.cache.model.menuitem.CachedMenuItem
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity
import javax.inject.Inject

/**
 * Defines mapping operations between cache and data layer for [MenuItemEntity].
 */
class MenuItemCacheMapper @Inject constructor(private val categoryMapper: FoodCategoryCacheMapper) :
        CacheEntityMapper<CachedMenuItem, MenuItemEntity> {

    override fun mapFromCached(cachedModel: CachedMenuItem): MenuItemEntity =
            MenuItemEntity(cachedModel.id, cachedModel.title, cachedModel.description,
                    cachedModel.image, cachedModel.price,
                    categoryMapper.mapFromCached(cachedModel.category!!))

    override fun mapToCached(dataModel: MenuItemEntity): CachedMenuItem =
            CachedMenuItem(dataModel.id, dataModel.title, dataModel.description,
                    dataModel.image, dataModel.price, categoryMapper.mapToCached(dataModel.category))
}
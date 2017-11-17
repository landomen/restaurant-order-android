package si.lanisnik.restaurantorder.cache.mapper.order

import si.lanisnik.restaurantorder.cache.mapper.CacheEntityMapper
import si.lanisnik.restaurantorder.cache.mapper.menuitem.MenuItemCacheMapper
import si.lanisnik.restaurantorder.cache.model.order.shoppingcart.CachedSelectedMenuItem
import si.lanisnik.restaurantorder.data.entity.orders.SelectedMenuItemEntity
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
class SelectedMenuItemCacheMapper @Inject constructor(private val menuItemMapper: MenuItemCacheMapper) :
        CacheEntityMapper<CachedSelectedMenuItem, SelectedMenuItemEntity> {

    override fun mapFromCached(cachedModel: CachedSelectedMenuItem): SelectedMenuItemEntity {
        return SelectedMenuItemEntity(cachedModel.id, menuItemMapper.mapFromCached(cachedModel.menuItem!!), cachedModel.comment)
    }

    override fun mapToCached(dataModel: SelectedMenuItemEntity): CachedSelectedMenuItem {
        return CachedSelectedMenuItem(dataModel.id, menuItemMapper.mapToCached(dataModel.menuItem), dataModel.comment)
    }
}
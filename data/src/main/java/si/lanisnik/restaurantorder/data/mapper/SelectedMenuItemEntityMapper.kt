package si.lanisnik.restaurantorder.data.mapper

import si.lanisnik.restaurantorder.data.entity.orders.SelectedMenuItemEntity
import si.lanisnik.restaurantorder.domain.model.order.SelectedMenuItem
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 17/11/2017.
 * domen.lanisnik@gmail.com
 */
class SelectedMenuItemEntityMapper @Inject constructor(private val menuItemMapper: MenuItemEntityMapper) :
        EntityMapper<SelectedMenuItemEntity, SelectedMenuItem> {

    override fun mapFromEntity(model: SelectedMenuItemEntity): SelectedMenuItem {
        return SelectedMenuItem(model.id, menuItemMapper.mapFromEntity(model.menuItem), model.comment)
    }

    override fun mapToEntity(model: SelectedMenuItem): SelectedMenuItemEntity {
        return SelectedMenuItemEntity(model.id, menuItemMapper.mapToEntity(model.menuItem), model.comment)
    }
}
package si.lanisnik.restaurantorder.remote.order.mapper

import si.lanisnik.restaurantorder.data.entity.orders.SelectedMenuItemEntity
import si.lanisnik.restaurantorder.remote.base.mapper.RemoteEntityMapper
import si.lanisnik.restaurantorder.remote.menuitem.mapper.MenuItemRemoteMapper
import si.lanisnik.restaurantorder.remote.order.model.create.SelectedMenuItemDto
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 18/11/2017.
 * domen.lanisnik@gmail.com
 */
class SelectedMenuItemRemoteMapper @Inject constructor(private val menuItemMapper: MenuItemRemoteMapper) :
        RemoteEntityMapper<SelectedMenuItemDto, SelectedMenuItemEntity> {

    override fun mapFromRemote(remoteModel: SelectedMenuItemDto): SelectedMenuItemEntity {
        return SelectedMenuItemEntity(
                remoteModel.id,
                menuItemMapper.mapFromRemote(remoteModel.menuItem),
                remoteModel.comment)
    }

    override fun mapToRemote(entity: SelectedMenuItemEntity): SelectedMenuItemDto {
        return SelectedMenuItemDto(
                entity.id,
                menuItemMapper.mapToRemote(entity.menuItem),
                entity.comment)
    }
}
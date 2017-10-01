package si.lanisnik.restaurantorder.data.remote.mapper

import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity
import si.lanisnik.restaurantorder.data.remote.model.menuitem.MenuItemDto
import javax.inject.Inject

/**
 * Map a [MenuItemDto] to [MenuItemEntity] instance when data is moving between this and data layer.
 */
open class MenuItemRemoteMapper @Inject constructor(private val foodCategoryRemoteMapper: FoodCategoryRemoteMapper) :
        RemoteEntityMapper<MenuItemDto, MenuItemEntity> {

    override fun mapFromRemote(remoteModel: MenuItemDto): MenuItemEntity =
            MenuItemEntity(remoteModel.id, remoteModel.title, remoteModel.description,
                    remoteModel.image, remoteModel.price, foodCategoryRemoteMapper.mapFromRemote(remoteModel.category))
}
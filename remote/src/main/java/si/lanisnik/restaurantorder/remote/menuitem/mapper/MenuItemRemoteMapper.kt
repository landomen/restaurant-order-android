package si.lanisnik.restaurantorder.remote.menuitem.mapper

import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity
import si.lanisnik.restaurantorder.remote.base.mapper.RemoteEntityMapper
import si.lanisnik.restaurantorder.remote.foodcategory.mapper.FoodCategoryRemoteMapper
import si.lanisnik.restaurantorder.remote.menuitem.model.MenuItemDto
import javax.inject.Inject

/**
 * Map a [MenuItemDto] to [MenuItemEntity] instance when data is moving between this and data layer.
 */
open class MenuItemRemoteMapper @Inject constructor(private val foodCategoryRemoteMapper: FoodCategoryRemoteMapper) :
        RemoteEntityMapper<MenuItemDto, MenuItemEntity> {

    override fun mapFromRemote(remoteModel: MenuItemDto): MenuItemEntity =
            MenuItemEntity(remoteModel.id, remoteModel.title, remoteModel.description,
                    remoteModel.image, remoteModel.price, foodCategoryRemoteMapper.mapFromRemote(remoteModel.category))

    override fun mapToRemote(entity: MenuItemEntity): MenuItemDto {
        return MenuItemDto(entity.id, entity.title, entity.description,
                entity.image, entity.price, foodCategoryRemoteMapper.mapToRemote(entity.category))
    }
}
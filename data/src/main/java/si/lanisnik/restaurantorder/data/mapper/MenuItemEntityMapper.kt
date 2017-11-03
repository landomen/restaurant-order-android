package si.lanisnik.restaurantorder.data.mapper

import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemEntityMapper @Inject constructor(private val foodCategoryMapper: FoodCategoryEntityMapper) :
        EntityMapper<MenuItemEntity, MenuItem> {

    override fun mapFromEntity(model: MenuItemEntity): MenuItem =
            MenuItem(model.id, model.title, model.description,
                    model.image, model.price, foodCategoryMapper.mapFromEntity(model.category))

    override fun mapToEntity(model: MenuItem): MenuItemEntity =
            MenuItemEntity(model.id, model.title, model.description,
                    model.image, model.price, foodCategoryMapper.mapToEntity(model.category))
}
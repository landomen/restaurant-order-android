package si.lanisnik.restaurantorder.mapper

import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import si.lanisnik.restaurantorder.menuitem.model.MenuItemModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemMapper @Inject constructor() : PresentationMapper<MenuItem, MenuItemModel> {

    override fun mapToModel(model: MenuItem): MenuItemModel =
            MenuItemModel(model.id, model.title, model.description, model.image, model.price, model.categoryId)
}
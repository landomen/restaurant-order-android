package si.lanisnik.restaurantorder.mapper

import si.lanisnik.restaurantorder.domain.model.order.SelectedMenuItem
import si.lanisnik.restaurantorder.ui.order.shoppingcart.model.ShoppingCartItemModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
class SelectedMenuItemModelMapper @Inject constructor(private val menuItemMapper: MenuItemMapper) :
        PresentationMapper<SelectedMenuItem, ShoppingCartItemModel> {

    override fun mapToModel(model: SelectedMenuItem): ShoppingCartItemModel {
        return ShoppingCartItemModel(model.id, menuItemMapper.mapToModel(model.menuItem), model.comment)
    }

    override fun mapFromModel(model: ShoppingCartItemModel): SelectedMenuItem {
        return SelectedMenuItem(model.id, menuItemMapper.mapFromModel(model.menuItem), model.comment)
    }
}
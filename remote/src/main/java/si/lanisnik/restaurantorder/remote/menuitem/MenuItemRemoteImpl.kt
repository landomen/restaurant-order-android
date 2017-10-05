package si.lanisnik.restaurantorder.remote.menuitem

import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity
import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemRemote
import si.lanisnik.restaurantorder.remote.menuitem.mapper.MenuItemRemoteMapper
import si.lanisnik.restaurantorder.remote.menuitem.service.MenuItemsService
import javax.inject.Inject

/**
 * Remote implementation for retrieving Menu items. This class implements the
 * [MenuItemRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class MenuItemRemoteImpl @Inject constructor(private val menuItemsService: MenuItemsService,
                                             private val mapper: MenuItemRemoteMapper) : MenuItemRemote {
    override fun getMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>> =
            menuItemsService.getMenuItems(categoryId)
                    .map { it.menuItems }
                    .map { menuItems ->
                        menuItems.map { mapper.mapFromRemote(it) }
                    }
}
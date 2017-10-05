package si.lanisnik.restaurantorder.data.repository.menuitem

import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity

/**
 * Interface defining methods for the retrieval of Menu items from Rest API. This is to be implemented by the
 * remote layer, using this interface as a way of communicating.
 */
interface MenuItemRemote {

    fun getMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>>

}
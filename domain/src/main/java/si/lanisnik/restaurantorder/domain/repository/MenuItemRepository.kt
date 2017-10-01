package si.lanisnik.restaurantorder.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem

/**
 * Interface defining methods for how the data layer can pass MenuItem data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface MenuItemRepository {

    fun getMenuItems(categoryId: Int): Flowable<List<MenuItem>>

    fun saveMenuItems(menuItems: List<MenuItem>): Completable

}
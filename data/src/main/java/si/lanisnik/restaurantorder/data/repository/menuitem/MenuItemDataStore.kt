package si.lanisnik.restaurantorder.data.repository.menuitem

import io.reactivex.Completable
import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity

/**
 * Interface defining methods for the data operations related to Menu items.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented.
 */
interface MenuItemDataStore {

    fun saveMenuItems(categoryId: Int): Completable
    fun getMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>>

}
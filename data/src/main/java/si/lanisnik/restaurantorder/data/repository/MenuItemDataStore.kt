package si.lanisnik.restaurantorder.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity

/**
 * Interface defining methods for the data operations related to Menu items and Categories.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented.
 */
interface MenuItemDataStore {

    // region Categories

    fun getCategories(): Flowable<List<FoodCategoryEntity>>
    fun saveCategories(categories: List<FoodCategoryEntity>): Completable

    // endregion

    // region Menu Item

    fun saveMenuItems(categoryId: Int): Completable
    fun getMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>>

    // endregion

}
package si.lanisnik.restaurantorder.domain.repository

import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.model.menuitem.FoodCategory
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem

/**
 * Created by Domen Lanišnik on 21/09/2017.
 * domen.lanisnik@gmail.com
 */
interface MenuItemRepository {

    fun getCategories(): Single<List<FoodCategory>>

    fun getMenuItems(categoryId: Int): Flowable<List<MenuItem>>

}
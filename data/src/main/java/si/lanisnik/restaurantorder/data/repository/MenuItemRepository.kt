package si.lanisnik.restaurantorder.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.net.RestApiClient
import si.lanisnik.restaurantorder.data.net.model.menuitem.AllMenuItemsResponse
import si.lanisnik.restaurantorder.data.net.model.menuitem.GroupedByCategory
import si.lanisnik.restaurantorder.domain.model.menuitem.FoodCategory
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import si.lanisnik.restaurantorder.domain.repository.MenuItemRepository

/**
 * Created by Domen Lanišnik on 24/09/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemRepository : MenuItemRepository {

    override fun getCategories(): Flowable<List<FoodCategory>> {
        return RestApiClient.menuItemsApi.getAllMenuItems()
                .flatMap { t: AllMenuItemsResponse -> Single.just(t.categories) }
                .map { t: List<GroupedByCategory> -> t.map { groupedByCategory -> FoodCategory(groupedByCategory.category.id, groupedByCategory.category.title) } }
                .toFlowable()
    }

    override fun getMenuItems(categoryId: Int): Flowable<List<MenuItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
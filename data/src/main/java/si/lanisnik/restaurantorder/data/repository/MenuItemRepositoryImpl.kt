package si.lanisnik.restaurantorder.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.remote.model.menuitem.FoodCategoryDto
import si.lanisnik.restaurantorder.data.remote.service.MenuItemsService
import si.lanisnik.restaurantorder.domain.model.menuitem.FoodCategory
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import si.lanisnik.restaurantorder.domain.repository.MenuItemRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Domen Lanišnik on 24/09/2017.
 * domen.lanisnik@gmail.com
 */
@Singleton
class MenuItemRepositoryImpl @Inject constructor(private val menuItemsService: MenuItemsService) : MenuItemRepository {

    override fun getCategories(): Single<List<FoodCategory>> {
        return menuItemsService.getFoodCategories()
                .flatMap { categoriesResponse -> Single.just(categoriesResponse.categories) }
                .map { list: List<FoodCategoryDto> -> list.map { FoodCategory(it.id, it.title) } }
    }

    override fun getMenuItems(categoryId: Int): Flowable<List<MenuItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
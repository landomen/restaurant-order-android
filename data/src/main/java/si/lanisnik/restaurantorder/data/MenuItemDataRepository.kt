package si.lanisnik.restaurantorder.data

import io.reactivex.Completable
import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.remote.service.MenuItemsService
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import si.lanisnik.restaurantorder.domain.repository.MenuItemRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Domen Lanišnik on 24/09/2017.
 * domen.lanisnik@gmail.com
 */
@Singleton
class MenuItemDataRepository @Inject constructor(private val menuItemsService: MenuItemsService) : MenuItemRepository {

    override fun saveMenuItems(menuItems: List<MenuItem>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMenuItems(categoryId: Int): Flowable<List<MenuItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
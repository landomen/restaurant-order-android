package si.lanisnik.restaurantorder.data.source.menuitem

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity
import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemDataStore
import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemRemote
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuitemRemoteDataStore @Inject constructor(private val menuItemRemote: MenuItemRemote) : MenuItemDataStore {

    override fun getMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>> = menuItemRemote.getMenuItems(categoryId)

    override fun saveMenuItems(categoryId: Int, menuItems: List<MenuItemEntity>): Completable {
        throw UnsupportedOperationException()
    }

    override fun clearMenuItems(categoryId: Int): Completable {
        throw UnsupportedOperationException()
    }

    override fun isCached(categoryId: Int): Single<Boolean> {
        throw UnsupportedOperationException()
    }


}
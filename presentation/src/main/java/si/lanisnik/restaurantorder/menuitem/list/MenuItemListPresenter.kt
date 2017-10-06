package si.lanisnik.restaurantorder.menuitem.list

import io.reactivex.subscribers.DisposableSubscriber
import si.lanisnik.restaurantorder.domain.interactor.menuitem.GetMenuItems
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import si.lanisnik.restaurantorder.menuitem.model.MenuItemModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 06/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemListPresenter @Inject constructor(private val view: MenuItemsListContract.View?,
                                                private val getMenuItems: GetMenuItems) : MenuItemsListContract.Presenter {

    override fun setView(view: MenuItemsListContract.View) {
    }

    override fun onStart() {
    }

    override fun onStop() {
        getMenuItems.dispose()
    }

    override fun initialize(categoryId: Int) {
        getMenuItems.execute(object : DisposableSubscriber<List<MenuItem>>() {

            override fun onError(t: Throwable?) {

            }

            override fun onNext(t: List<MenuItem>) {
                view?.showMenuItems(t.map { MenuItemModel(it.id, it.title, it.description, it.image, it.price, it.categoryId) })
            }

            override fun onComplete() {
            }

        }, categoryId)
    }
}
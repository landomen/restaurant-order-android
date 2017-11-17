package si.lanisnik.restaurantorder.domain.interactor.order

import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import si.lanisnik.restaurantorder.domain.model.order.SelectedMenuItem
import si.lanisnik.restaurantorder.domain.repository.OrderRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Domen Lanišnik on 10/11/2017.
 * domen.lanisnik@gmail.com
 */
@Singleton
class ShoppingCartInteractor @Inject constructor(private val repository: OrderRepository) : ShoppingCart {
    private val selectedMenuItems = mutableListOf<SelectedMenuItem>()
    private val totalCountSubject: BehaviorSubject<Int> = BehaviorSubject.create<Int>()

    init {
        repository.loadShoppingCart()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe { items: List<SelectedMenuItem> ->
                    selectedMenuItems.addAll(items)
                    updateTotalCount()
                }
    }

    override fun addItem(item: MenuItem, comment: String) {
        val selectedMenuItem = SelectedMenuItem(System.currentTimeMillis(), item, parseComment(comment))
        selectedMenuItems.add(selectedMenuItem)
        updateTotalCount()

        repository.addItemToShoppingCart(selectedMenuItem)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe { }
    }

    override fun deleteSelectedItem(id: Int) {
        selectedMenuItems.removeAt(indexOfItem(id))
        updateTotalCount()
    }

    override fun getSelectedMenuItems(): List<SelectedMenuItem> = selectedMenuItems

    override fun getTotalCountObservable(): BehaviorSubject<Int> = totalCountSubject

    private fun indexOfItem(id: Int): Int = selectedMenuItems.indexOfFirst { it.menuItem.id == id }

    private fun updateTotalCount() {
        totalCountSubject.onNext(selectedMenuItems.size)
    }

    private fun parseComment(comment: String): String? = if (comment.isEmpty())
        null
    else
        comment

}
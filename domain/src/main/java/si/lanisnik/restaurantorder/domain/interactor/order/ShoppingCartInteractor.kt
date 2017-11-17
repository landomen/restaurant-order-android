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
    private val itemsCountSubject: BehaviorSubject<Int> = BehaviorSubject.create()

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
        saveNewItem(selectedMenuItem)
        updateTotalCount()
    }

    override fun deleteSelectedItem(id: Long) {
        val indexOfItem = indexOfItem(id)
        if (indexOfItem != -1) {
            selectedMenuItems.removeAt(indexOfItem(id))
            deleteItem(id)
            updateTotalCount()
        }
    }

    override fun getSelectedMenuItems(): List<SelectedMenuItem> = selectedMenuItems

    override fun getItemsCountObservable(): BehaviorSubject<Int> = itemsCountSubject

    override fun getTotalCost(): Double = selectedMenuItems.sumByDouble { it.menuItem.price }

    private fun indexOfItem(id: Long): Int = selectedMenuItems.indexOfFirst { it.id == id }

    private fun updateTotalCount() {
        itemsCountSubject.onNext(selectedMenuItems.size)
    }

    /**
     * Saves the new selected item locally.
     */
    private fun saveNewItem(selectedMenuItem: SelectedMenuItem) {
        repository.addItemToShoppingCart(selectedMenuItem)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe { }
    }

    /**
     * Deletes the item from local storage.
     */
    private fun deleteItem(id: Long) {
        repository.removeItemFromShoppingCart(id)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe { }
    }

    private fun parseComment(comment: String): String?
            = if (comment.isEmpty()) null else comment

}
package si.lanisnik.restaurantorder.domain.interactor.order

import io.reactivex.subjects.BehaviorSubject
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import si.lanisnik.restaurantorder.domain.model.order.SelectedMenuItem

/**
 * Created by Domen Lanišnik on 17/11/2017.
 * domen.lanisnik@gmail.com
 */
interface ShoppingCart {

    fun addItem(item: MenuItem, comment: String)
    fun deleteSelectedItem(id: Long)

    fun getSelectedMenuItems(): List<SelectedMenuItem>
    fun getTotalCountObservable(): BehaviorSubject<Int>

}
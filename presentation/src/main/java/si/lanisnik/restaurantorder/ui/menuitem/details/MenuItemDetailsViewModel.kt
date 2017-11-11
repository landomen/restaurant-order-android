package si.lanisnik.restaurantorder.ui.menuitem.details

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import si.lanisnik.restaurantorder.domain.model.order.ShoppingCart
import si.lanisnik.restaurantorder.ui.menuitem.model.MenuItemModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 30/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemDetailsViewModel @Inject constructor(private val shoppingCart: ShoppingCart) : ViewModel() {

    private lateinit var menuItem: MenuItemModel

    private val shoppingCartObservable: MutableLiveData<Int> = MutableLiveData()

    fun initialize(menuItem: MenuItemModel) {
        this.menuItem = menuItem
        postShoppingCartUpdate()
    }

    fun getShoppingCartObservable(): LiveData<Int> = shoppingCartObservable

    fun getTitle(): String = menuItem.title

    fun getMenuItemDescription(): String = menuItem.description

    fun getMenuItemImageUrl(): String? = menuItem.image

    fun getMenuItemPrice(): String = menuItem.getFormattedPrice()

    fun addToShoppingCart() {
        shoppingCart.addItem(menuItem.id)
        postShoppingCartUpdate()
    }

    fun updateShoppingCart(count: Int, comment: String) {
        shoppingCart.updateItem(menuItem.id, count, comment)
        postShoppingCartUpdate()
    }

    fun deleteFromShoppingCart() {
        shoppingCart.deleteItem(menuItem.id)
        postShoppingCartUpdate()
    }

    private fun postShoppingCartUpdate() {
        shoppingCartObservable.postValue(shoppingCart.totalCount)
    }

}
package si.lanisnik.restaurantorder.ui.order.shoppingcart

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart
import si.lanisnik.restaurantorder.mapper.SelectedMenuItemModelMapper
import si.lanisnik.restaurantorder.ui.order.shoppingcart.model.ShoppingCartItemModel

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderPreviewViewModel(private val shoppingCart: ShoppingCart,
                            private val mapper: SelectedMenuItemModelMapper) : ViewModel() {

    private val itemsLiveData: MutableLiveData<List<ShoppingCartItemModel>> = MutableLiveData()

    fun initialize() {
        loadShoppingCartItems()
    }

    fun getItemsObservable(): LiveData<List<ShoppingCartItemModel>> = itemsLiveData

    fun removeItem(item: ShoppingCartItemModel) {
        shoppingCart.deleteSelectedItem(item.id)
        loadShoppingCartItems()
    }

    private fun loadShoppingCartItems() {
        itemsLiveData.postValue(shoppingCart.getSelectedMenuItems().map { mapper.mapToModel(it) })
    }

}
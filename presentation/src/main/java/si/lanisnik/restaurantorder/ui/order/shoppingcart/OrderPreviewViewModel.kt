package si.lanisnik.restaurantorder.ui.order.shoppingcart

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.domain.interactor.customer.GetCustomerType
import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart
import si.lanisnik.restaurantorder.domain.model.customer.CustomerType
import si.lanisnik.restaurantorder.mapper.SelectedMenuItemModelMapper
import si.lanisnik.restaurantorder.ui.order.shoppingcart.model.ShoppingCartItemModel

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderPreviewViewModel(private val shoppingCart: ShoppingCart,
                            private val getCustomerType: GetCustomerType,
                            private val mapper: SelectedMenuItemModelMapper) : ViewModel() {

    private val totalCostLiveData: MutableLiveData<String> = MutableLiveData()
    private val itemsLiveData: MutableLiveData<List<ShoppingCartItemModel>> = MutableLiveData()
    private val continueLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCleared() {
        getCustomerType.dispose()
        super.onCleared()
    }

    fun initialize() {
        loadShoppingCartItems()
        updateTotalCost()
    }

    fun getItemsObservable(): LiveData<List<ShoppingCartItemModel>> = itemsLiveData

    fun getTotalCostObservable(): LiveData<String> = totalCostLiveData

    fun getCanContinueObservable(): LiveData<Boolean> = continueLiveData

    fun removeItem(item: ShoppingCartItemModel) {
        shoppingCart.deleteSelectedItem(item.id)
        loadShoppingCartItems()
        updateTotalCost()
    }

    fun onNextStepClicked() {
        getCustomerType.execute(Consumer {
            continueLiveData.postValue(it == CustomerType.LOGGED_IN)
        }, Consumer {
            continueLiveData.postValue(false)
        })
    }

    private fun loadShoppingCartItems() {
        itemsLiveData.postValue(shoppingCart.getSelectedMenuItems().map { mapper.mapToModel(it) })
    }

    private fun updateTotalCost() {
        val totalCost = shoppingCart.getTotalCost()
        if (totalCost > 0) {
            totalCostLiveData.postValue("${totalCost}€")
        }
    }

}
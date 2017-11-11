package si.lanisnik.restaurantorder.ui.menuitem.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.subscribers.DisposableSubscriber
import si.lanisnik.restaurantorder.domain.interactor.menuitem.GetMenuItems
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import si.lanisnik.restaurantorder.domain.model.order.ShoppingCart
import si.lanisnik.restaurantorder.mapper.MenuItemMapper
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.foodcategory.model.FoodCategoryModel
import si.lanisnik.restaurantorder.ui.menuitem.model.MenuItemModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemListViewModel @Inject constructor(private val getMenuItems: GetMenuItems,
                                                private val mapper: MenuItemMapper,
                                                private val shoppingCart: ShoppingCart) : ViewModel() {

    private val menuItemsLiveData: MutableLiveData<Resource<List<MenuItemModel>>> = MutableLiveData()
    private val shoppingCartObservable: MutableLiveData<Int> = MutableLiveData()
    private lateinit var foodCategory: FoodCategoryModel

    override fun onCleared() {
        getMenuItems.dispose()
        super.onCleared()
    }

    fun initialize(foodCategory: FoodCategoryModel) {
        this.foodCategory = foodCategory
        shoppingCartObservable.postValue(shoppingCart.totalCount)
        loadMenuItems()
    }

    /**
     * Get [LiveData] for observing state of Menu items fetching.
     */
    fun getMenuItems(): LiveData<Resource<List<MenuItemModel>>> = menuItemsLiveData

    fun getShoppingCartObservable(): LiveData<Int> = shoppingCartObservable

    fun retry() {
        loadMenuItems()
    }

    private fun loadMenuItems() {
        menuItemsLiveData.postValue(Resource.loading())
        getMenuItems.execute(MenuItemsSubscriber(), foodCategory.id)
    }

    inner class MenuItemsSubscriber : DisposableSubscriber<List<MenuItem>>() {

        override fun onError(t: Throwable?) {
            menuItemsLiveData.postValue(Resource.error())
        }

        override fun onNext(t: List<MenuItem>) {
            menuItemsLiveData.postValue(Resource.success(t.map {
                mapper.mapToModel(it)
            }))
        }

        override fun onComplete() {
        }

    }

}
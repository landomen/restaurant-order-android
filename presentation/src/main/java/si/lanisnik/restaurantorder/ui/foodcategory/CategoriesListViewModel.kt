package si.lanisnik.restaurantorder.ui.foodcategory

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.subscribers.DisposableSubscriber
import si.lanisnik.restaurantorder.domain.interactor.foodcategory.GetCategories
import si.lanisnik.restaurantorder.domain.model.foodcategory.FoodCategory
import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart
import si.lanisnik.restaurantorder.mapper.FoodCategoryMapper
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.base.viewmodels.ShoppingCartViewModel
import si.lanisnik.restaurantorder.ui.foodcategory.model.FoodCategoryModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 07/10/2017.
 * domen.lanisnik@gmail.com
 */
class CategoriesListViewModel @Inject constructor(private val getCategories: GetCategories,
                                                  private val mapper: FoodCategoryMapper,
                                                  shoppingCart: ShoppingCart) : ShoppingCartViewModel(shoppingCart) {

    private val categoriesLiveData: MutableLiveData<Resource<List<FoodCategoryModel>>> = MutableLiveData()

    fun initialize() {
        loadCategories()
        initShoppingCart()
    }

    override fun onCleared() {
        getCategories.dispose()
        super.onCleared()
    }

    /**
     * Get [LiveData] for observing state of Categories fetching.
     */
    fun getCategories(): LiveData<Resource<List<FoodCategoryModel>>> = categoriesLiveData

    /**
     * Try to fetch data again.
     */
    fun retry() {
        loadCategories()
    }

    /**
     * Starts retrieving categories.
     */
    private fun loadCategories() {
        categoriesLiveData.postValue(Resource.loading())
        getCategories.execute(CategoriesSubscriber())
    }

    inner class CategoriesSubscriber : DisposableSubscriber<List<FoodCategory>>() {

        override fun onNext(t: List<FoodCategory>) {
            categoriesLiveData.postValue(Resource.success(t.map {
                mapper.mapToModel(it)
            }))
        }

        override fun onComplete() {

        }

        override fun onError(e: Throwable) {
            categoriesLiveData.postValue(Resource.error())
        }

    }

}
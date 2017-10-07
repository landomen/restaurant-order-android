package si.lanisnik.restaurantorder.foodcategory

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.subscribers.DisposableSubscriber
import si.lanisnik.restaurantorder.base.data.Resource
import si.lanisnik.restaurantorder.base.data.ResourceState
import si.lanisnik.restaurantorder.domain.interactor.foodcategory.GetCategories
import si.lanisnik.restaurantorder.domain.model.foodcategory.FoodCategory
import si.lanisnik.restaurantorder.foodcategory.model.FoodCategoryModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 07/10/2017.
 * domen.lanisnik@gmail.com
 */
class CategoriesListViewModel @Inject constructor(private val getCategories: GetCategories) : ViewModel() {

    private val categoriesLiveData: MutableLiveData<Resource<List<FoodCategoryModel>>> = MutableLiveData()

    init {
        loadCategories()
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
     * Starts retrieving categories.
     */
    fun loadCategories() {
        categoriesLiveData.postValue(Resource(ResourceState.LOADING))
        getCategories.execute(CategoriesSubscriber())
    }

    inner class CategoriesSubscriber : DisposableSubscriber<List<FoodCategory>>() {

        override fun onNext(t: List<FoodCategory>) {
            categoriesLiveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { FoodCategoryModel(it.id, it.title) }))
        }

        override fun onComplete() {

        }

        override fun onError(e: Throwable) {
            categoriesLiveData.postValue(Resource(ResourceState.ERROR))
        }

    }

}
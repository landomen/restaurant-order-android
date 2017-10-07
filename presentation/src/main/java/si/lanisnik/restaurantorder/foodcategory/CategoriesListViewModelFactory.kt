package si.lanisnik.restaurantorder.foodcategory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.foodcategory.GetCategories

/**
 * Created by Domen Lanišnik on 07/10/2017.
 * domen.lanisnik@gmail.com
 */
class CategoriesListViewModelFactory(private val getCategories: GetCategories) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesListViewModel::class.java))
            return CategoriesListViewModel(getCategories) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
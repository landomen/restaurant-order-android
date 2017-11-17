package si.lanisnik.restaurantorder.ui.foodcategory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.foodcategory.GetCategories
import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart
import si.lanisnik.restaurantorder.mapper.FoodCategoryMapper

/**
 * Created by Domen Lanišnik on 07/10/2017.
 * domen.lanisnik@gmail.com
 */
class CategoriesListViewModelFactory(private val getCategories: GetCategories,
                                     private val mapper: FoodCategoryMapper,
                                     private val shoppingCart: ShoppingCart) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesListViewModel::class.java))
            return CategoriesListViewModel(getCategories, mapper, shoppingCart) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
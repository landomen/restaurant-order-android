package si.lanisnik.restaurantorder.ui.menuitem.details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 30/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemDetailsViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuItemDetailsViewModel::class.java))
            return MenuItemDetailsViewModel() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
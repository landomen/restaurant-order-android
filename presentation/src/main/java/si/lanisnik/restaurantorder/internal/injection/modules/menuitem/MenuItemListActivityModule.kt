package si.lanisnik.restaurantorder.internal.injection.modules.menuitem

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.menuitem.GetMenuItems
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.mapper.MenuItemMapper
import si.lanisnik.restaurantorder.menuitem.list.MenuItemsListViewModelFactory

/**
 * Created by Domen Lanišnik on 06/10/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class MenuItemListActivityModule {

    @PerActivity
    @Provides
    fun provideMenuItemListViewModelFactory(getMenuItems: GetMenuItems, mapper: MenuItemMapper): MenuItemsListViewModelFactory =
            MenuItemsListViewModelFactory(getMenuItems, mapper)
}
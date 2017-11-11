package si.lanisnik.restaurantorder.internal.injection.modules.menuitem

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.menuitem.GetMenuItems
import si.lanisnik.restaurantorder.domain.model.order.ShoppingCart
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.mapper.MenuItemMapper
import si.lanisnik.restaurantorder.ui.menuitem.details.MenuItemDetailsViewModelFactory
import si.lanisnik.restaurantorder.ui.menuitem.list.MenuItemsListViewModelFactory

/**
 * Created by Domen Lanišnik on 06/10/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class MenuItemActivityModule {

    @PerActivity
    @Provides
    fun provideMenuItemListViewModelFactory(getMenuItems: GetMenuItems,
                                            mapper: MenuItemMapper,
                                            shoppingCart: ShoppingCart): MenuItemsListViewModelFactory =
            MenuItemsListViewModelFactory(getMenuItems, mapper, shoppingCart)

    @PerActivity
    @Provides
    fun provideMenuItemDetailsViewModelFactory(shoppingCart: ShoppingCart): MenuItemDetailsViewModelFactory =
            MenuItemDetailsViewModelFactory(shoppingCart)
}
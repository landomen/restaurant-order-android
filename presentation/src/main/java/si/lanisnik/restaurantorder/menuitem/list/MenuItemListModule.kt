package si.lanisnik.restaurantorder.menuitem.list

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.menuitem.GetMenuItems
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity

/**
 * Created by Domen Lanišnik on 06/10/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class MenuItemListModule {

    @PerActivity
    @Provides
    fun provideMenuItemListView(menuItemsListActivity: MenuItemsListActivity): MenuItemsListContract.View =
            menuItemsListActivity

    @PerActivity
    @Provides
    fun provideMenuItemListPresenter(view: MenuItemsListContract.View, getMenuItems: GetMenuItems): MenuItemsListContract.Presenter =
            MenuItemListPresenter(view, getMenuItems)
}
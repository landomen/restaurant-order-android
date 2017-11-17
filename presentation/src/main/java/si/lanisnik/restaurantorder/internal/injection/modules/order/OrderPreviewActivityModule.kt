package si.lanisnik.restaurantorder.internal.injection.modules.order

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.mapper.SelectedMenuItemModelMapper
import si.lanisnik.restaurantorder.ui.order.shoppingcart.OrderPreviewViewModelFactory

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class OrderPreviewActivityModule {

    @PerActivity
    @Provides
    fun provideOrderPreviewViewModelFactory(shoppingCart: ShoppingCart,
                                            mapper: SelectedMenuItemModelMapper): OrderPreviewViewModelFactory =
            OrderPreviewViewModelFactory(shoppingCart, mapper)

}
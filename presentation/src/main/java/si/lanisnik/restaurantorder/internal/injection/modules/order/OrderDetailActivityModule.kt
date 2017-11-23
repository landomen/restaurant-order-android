package si.lanisnik.restaurantorder.internal.injection.modules.order

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.order.GetOrderHistoryDetails
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.mapper.OrderModelMapper
import si.lanisnik.restaurantorder.ui.order.detail.OrderDetailViewModelFactory

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class OrderDetailActivityModule {

    @PerActivity
    @Provides
    fun provideOrderDetailViewModelFactory(getOrderHistoryDetails: GetOrderHistoryDetails,
                                           orderMapper: OrderModelMapper): OrderDetailViewModelFactory =
            OrderDetailViewModelFactory(getOrderHistoryDetails, orderMapper)
}
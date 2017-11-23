package si.lanisnik.restaurantorder.internal.injection.modules.order

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.order.GetHistoryOrders
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.mapper.OrderHistoryModelMapper
import si.lanisnik.restaurantorder.ui.order.history.OrderHistoryListViewModelFactory

/**
 * Created by Domen Lanišnik on 20/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class OrderHistoryListActivityModule {

    @PerActivity
    @Provides
    fun provideOrderHistoryListViewModelFactory(getHistoryOrders: GetHistoryOrders,
                                                orderMapper: OrderHistoryModelMapper): OrderHistoryListViewModelFactory =
            OrderHistoryListViewModelFactory(getHistoryOrders, orderMapper)
}
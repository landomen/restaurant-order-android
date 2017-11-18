package si.lanisnik.restaurantorder.internal.injection.modules.order

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.cache.OrderCacheImpl
import si.lanisnik.restaurantorder.cache.mapper.order.SelectedMenuItemCacheMapper
import si.lanisnik.restaurantorder.data.OrderDataRepository
import si.lanisnik.restaurantorder.data.repository.order.OrderCache
import si.lanisnik.restaurantorder.data.repository.order.OrderRemote
import si.lanisnik.restaurantorder.domain.repository.OrderRepository
import si.lanisnik.restaurantorder.internal.injection.scopes.PerApplication
import si.lanisnik.restaurantorder.remote.RestaurantOrderServiceFactory
import si.lanisnik.restaurantorder.remote.order.OrderRemoteImpl
import si.lanisnik.restaurantorder.remote.order.mapper.SelectedMenuItemRemoteMapper
import si.lanisnik.restaurantorder.remote.order.service.OrdersService

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class OrderRepositoryModule {

    @Provides
    @PerApplication
    fun provideOrderRepository(repository: OrderDataRepository): OrderRepository = repository

    @Provides
    @PerApplication
    fun provideOrderCache(mapper: SelectedMenuItemCacheMapper): OrderCache =
            OrderCacheImpl(mapper)

    @Provides
    @PerApplication
    fun provideOrderRemote(service: OrdersService,
                           mapper: SelectedMenuItemRemoteMapper): OrderRemote =
            OrderRemoteImpl(service, mapper)

    @Provides
    @PerApplication
    fun provideOrderService(serviceFactory: RestaurantOrderServiceFactory): OrdersService =
            serviceFactory.makeService(OrdersService::class.java)

}
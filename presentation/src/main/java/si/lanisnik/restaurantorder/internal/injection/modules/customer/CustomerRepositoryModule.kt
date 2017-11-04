package si.lanisnik.restaurantorder.internal.injection.modules.customer

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.cache.CustomerCacheImpl
import si.lanisnik.restaurantorder.cache.mapper.customer.CustomerCacheMapper
import si.lanisnik.restaurantorder.data.CustomerDataRepository
import si.lanisnik.restaurantorder.data.repository.customer.CustomerCache
import si.lanisnik.restaurantorder.data.repository.customer.CustomerRemote
import si.lanisnik.restaurantorder.domain.repository.CustomerRepository
import si.lanisnik.restaurantorder.internal.injection.scopes.PerApplication
import si.lanisnik.restaurantorder.remote.RestaurantOrderServiceFactory
import si.lanisnik.restaurantorder.remote.base.RemoteExceptionMapper
import si.lanisnik.restaurantorder.remote.customer.CustomerRemoteImpl
import si.lanisnik.restaurantorder.remote.customer.mapper.CustomerRemoteMapper
import si.lanisnik.restaurantorder.remote.customer.service.CustomerService

/**
 * Created by Domen Lanišnik on 03/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class CustomerRepositoryModule {

    @Provides
    @PerApplication
    fun provideCustomerRepository(foodCategoryRepository: CustomerDataRepository): CustomerRepository = foodCategoryRepository

    @Provides
    @PerApplication
    fun provideCustomerCache(mapper: CustomerCacheMapper): CustomerCache =
            CustomerCacheImpl(mapper)

    @Provides
    @PerApplication
    fun provideCustomerRemote(service: CustomerService,
                              mapper: CustomerRemoteMapper,
                              exceptionMapper: RemoteExceptionMapper): CustomerRemote =
            CustomerRemoteImpl(service, mapper, exceptionMapper)

    @Provides
    @PerApplication
    fun provideCustomerService(serviceFactory: RestaurantOrderServiceFactory): CustomerService =
            serviceFactory.makeService(CustomerService::class.java)
}
package si.lanisnik.restaurantorder.internal.injection.modules.address

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.data.AddressDataRepository
import si.lanisnik.restaurantorder.data.repository.address.AddressRemote
import si.lanisnik.restaurantorder.domain.repository.AddressRepository
import si.lanisnik.restaurantorder.internal.injection.scopes.PerApplication
import si.lanisnik.restaurantorder.remote.RestaurantOrderServiceFactory
import si.lanisnik.restaurantorder.remote.address.AddressRemoteImpl
import si.lanisnik.restaurantorder.remote.address.mapper.AddressRemoteMapper
import si.lanisnik.restaurantorder.remote.address.service.AddressService
import si.lanisnik.restaurantorder.remote.base.RemoteExceptionMapper

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class AddressRepositoryModule {

    @Provides
    @PerApplication
    fun provideAddressRepository(addressRepository: AddressDataRepository): AddressRepository = addressRepository

//    @Provides
//    @PerApplication
//    fun provideAddressCache(mapper: AddressCacheMapper,
//                             simpleStorage: SimpleStorage): AddressCache =
//            AddressCacheImpl(mapper, simpleStorage)

    @Provides
    @PerApplication
    fun provideAddressRemote(service: AddressService,
                             mapper: AddressRemoteMapper,
                             exceptionMapper: RemoteExceptionMapper): AddressRemote =
            AddressRemoteImpl(service, mapper, exceptionMapper)

    @Provides
    @PerApplication
    fun provideAddressService(serviceFactory: RestaurantOrderServiceFactory): AddressService =
            serviceFactory.makeService(AddressService::class.java)
}
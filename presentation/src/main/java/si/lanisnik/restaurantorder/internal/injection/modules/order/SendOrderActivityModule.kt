package si.lanisnik.restaurantorder.internal.injection.modules.order

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.address.GetDeliveryAddresses
import si.lanisnik.restaurantorder.domain.interactor.order.CreateOrder
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.mapper.AddressModelMapper
import si.lanisnik.restaurantorder.ui.order.send.SendOrderViewModelFactory

/**
 * Created by Domen Lanišnik on 18/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class SendOrderActivityModule {

    @PerActivity
    @Provides
    fun provideSendOrderViewModelFactory(getDeliveryAddresses: GetDeliveryAddresses,
                                         createOrder: CreateOrder,
                                         addressMapper: AddressModelMapper): SendOrderViewModelFactory =
            SendOrderViewModelFactory(getDeliveryAddresses, createOrder, addressMapper)

}
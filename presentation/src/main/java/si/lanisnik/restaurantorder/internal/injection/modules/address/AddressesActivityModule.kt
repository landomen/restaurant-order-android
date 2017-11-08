package si.lanisnik.restaurantorder.internal.injection.modules.address

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.address.AddDeliveryAddress
import si.lanisnik.restaurantorder.domain.interactor.address.DeleteDeliveryAddress
import si.lanisnik.restaurantorder.domain.interactor.address.GetDeliveryAddresses
import si.lanisnik.restaurantorder.domain.interactor.address.SelectDefaultDeliveryAddress
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.mapper.AddressModelMapper
import si.lanisnik.restaurantorder.ui.address.AddressViewModelFactory

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class AddressesActivityModule {

    @PerActivity
    @Provides
    fun provideAddressViewModelFactory(getDeliveryAddresses: GetDeliveryAddresses,
                                       deleteDeliveryAddress: DeleteDeliveryAddress,
                                       addDeliveryAddress: AddDeliveryAddress,
                                       selectDefaultDeliveryAddress: SelectDefaultDeliveryAddress,
                                       mapper: AddressModelMapper): AddressViewModelFactory =
            AddressViewModelFactory(getDeliveryAddresses, deleteDeliveryAddress,
                    addDeliveryAddress, selectDefaultDeliveryAddress, mapper)

}
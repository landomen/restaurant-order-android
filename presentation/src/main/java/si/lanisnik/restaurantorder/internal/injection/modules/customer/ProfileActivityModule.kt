package si.lanisnik.restaurantorder.internal.injection.modules.customer

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.customer.GetCustomer
import si.lanisnik.restaurantorder.domain.interactor.customer.LogoutCustomer
import si.lanisnik.restaurantorder.domain.interactor.customer.UpdateCustomerProfile
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.mapper.CustomerModelMapper
import si.lanisnik.restaurantorder.ui.base.utilities.PhoneNumberValidator
import si.lanisnik.restaurantorder.ui.customer.profile.ProfileViewModelFactory

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class ProfileActivityModule {

    @PerActivity
    @Provides
    fun provideProfileViewModelFactory(getCustomer: GetCustomer,
                                       updateCustomerProfile: UpdateCustomerProfile,
                                       logoutCustomer: LogoutCustomer,
                                       customerModelMapper: CustomerModelMapper,
                                       phoneNumberValidator: PhoneNumberValidator): ProfileViewModelFactory =
            ProfileViewModelFactory(getCustomer, updateCustomerProfile,
                    logoutCustomer, customerModelMapper, phoneNumberValidator)
}
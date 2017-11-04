package si.lanisnik.restaurantorder.internal.injection.modules.customer

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.customer.RegisterCustomer
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.mapper.CustomerModelMapper
import si.lanisnik.restaurantorder.ui.onboarding.register.RegisterViewModelFactory
import si.lanisnik.restaurantorder.ui.base.utilities.PhoneNumberValidator

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class RegisterActivityModule {

    @PerActivity
    @Provides
    fun provideRegisterViewModelFactory(registerCustomer: RegisterCustomer,
                                        customerModelMapper: CustomerModelMapper,
                                        phoneNumberValidator: PhoneNumberValidator): RegisterViewModelFactory =
            RegisterViewModelFactory(registerCustomer, customerModelMapper, phoneNumberValidator)

}
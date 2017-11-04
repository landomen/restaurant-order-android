package si.lanisnik.restaurantorder.internal.injection.modules.customer

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.customer.RegisterCustomer
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.ui.onboarding.register.RegisterViewModelFactory

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class RegisterActivityModule {

    @PerActivity
    @Provides
    fun provideRegisterViewModelFactory(registerCustomer: RegisterCustomer): RegisterViewModelFactory =
            RegisterViewModelFactory(registerCustomer)

}
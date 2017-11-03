package si.lanisnik.restaurantorder.internal.injection.modules.customer

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.customer.LoginCustomer
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.ui.onboarding.login.LoginViewModelFactory

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class LoginActivityModule {

    @PerActivity
    @Provides
    fun provideLoginViewModelFactory(loginCustomer: LoginCustomer): LoginViewModelFactory =
            LoginViewModelFactory(loginCustomer)

}
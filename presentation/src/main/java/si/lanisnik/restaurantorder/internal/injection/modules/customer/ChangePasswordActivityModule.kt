package si.lanisnik.restaurantorder.internal.injection.modules.customer

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.customer.ChangePassword
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.ui.customer.password.ChangePasswordViewModelFactory

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class ChangePasswordActivityModule {

    @PerActivity
    @Provides
    fun provideChangePasswordViewModelFactory(changePassword: ChangePassword): ChangePasswordViewModelFactory =
            ChangePasswordViewModelFactory(changePassword)
}
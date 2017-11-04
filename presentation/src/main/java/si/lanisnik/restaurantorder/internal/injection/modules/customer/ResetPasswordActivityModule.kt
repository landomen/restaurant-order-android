package si.lanisnik.restaurantorder.internal.injection.modules.customer

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.customer.ResetPassword
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.ui.onboarding.password.ResetPasswordViewModelFactory

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class ResetPasswordActivityModule {

    @PerActivity
    @Provides
    fun provideResetPasswordViewModelFactory(resetPassword: ResetPassword): ResetPasswordViewModelFactory =
            ResetPasswordViewModelFactory(resetPassword)
}
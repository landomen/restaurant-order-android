package si.lanisnik.restaurantorder.internal.injection.modules.dashboard

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.customer.GetCustomerType
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.ui.dashboard.DashboardViewModelFactory

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class DashboardActivityModule {

    @PerActivity
    @Provides
    fun provideDashboardViewModelFactory(getCustomerType: GetCustomerType): DashboardViewModelFactory =
            DashboardViewModelFactory(getCustomerType)

}
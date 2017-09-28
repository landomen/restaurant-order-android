package si.lanisnik.restaurantorder.ui.categories

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.usecases.GetCategories
import si.lanisnik.restaurantorder.internal.di.scopes.PerActivity

/**
 * Created by Domen Lanišnik on 27/09/2017.
 * domen.lanisnik@gmail.com
 */
@Module
abstract class CategoriesListModule {

    @PerActivity
    @Provides
    fun provideCategoriesListPresenter(getCategories: GetCategories): CategoriesContract.Presenter {
        return CategoriesListPresenter(getCategories)
    }

}
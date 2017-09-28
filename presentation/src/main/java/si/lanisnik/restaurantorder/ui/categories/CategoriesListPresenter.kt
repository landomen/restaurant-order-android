package si.lanisnik.restaurantorder.ui.categories

import android.util.Log
import io.reactivex.observers.DisposableSingleObserver
import si.lanisnik.restaurantorder.domain.interactor.usecases.GetCategories
import si.lanisnik.restaurantorder.domain.model.menuitem.FoodCategory
import si.lanisnik.restaurantorder.internal.di.scopes.PerActivity
import si.lanisnik.restaurantorder.ui.categories.model.FoodCategoryModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 26/09/2017.
 * domen.lanisnik@gmail.com
 */
@PerActivity
class CategoriesListPresenter @Inject constructor(
        private val view: CategoriesContract.View?,
        private val getCategories: GetCategories) : CategoriesContract.Presenter {

    override fun setView(view: CategoriesContract.View) {
    }

    override fun onStart() {
        getCategories.execute(Subscriber())

    }

    override fun onStop() {
        getCategories.dispose()
    }

    inner class Subscriber : DisposableSingleObserver<List<FoodCategory>>() {

        override fun onSuccess(t: List<FoodCategory>) {
            Log.e("NEKI", "Success")
            view?.showCategories(t.map { FoodCategoryModel(it.id, it.title) })
        }

        override fun onError(e: Throwable) {
            Log.e("NEKI", "Error")
        }

    }
}
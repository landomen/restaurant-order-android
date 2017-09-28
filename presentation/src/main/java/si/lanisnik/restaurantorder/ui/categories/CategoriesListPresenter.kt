package si.lanisnik.restaurantorder.ui.categories

import android.util.Log
import io.reactivex.FlowableSubscriber
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.ResourceSubscriber
import org.reactivestreams.Subscription
import si.lanisnik.restaurantorder.domain.interactor.usecases.GetCategories
import si.lanisnik.restaurantorder.domain.model.menuitem.FoodCategory
import si.lanisnik.restaurantorder.internal.di.scopes.PerActivity
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 26/09/2017.
 * domen.lanisnik@gmail.com
 */
@PerActivity
class CategoriesListPresenter @Inject constructor(private val getCategories: GetCategories) : CategoriesContract.Presenter {

    override fun setView(view: CategoriesContract.View) {
    }

    override fun onStart() {
        getCategories.execute(Subscriber())

    }

    override fun onStop() {
    }

    inner class Subscriber: DisposableSingleObserver<List<FoodCategory>>() {

        override fun onSuccess(t: List<FoodCategory>) {
            Log.e("NEKI", "Success")
        }

        override fun onError(e: Throwable) {
            Log.e("NEKI", "Error")
        }

    }
}
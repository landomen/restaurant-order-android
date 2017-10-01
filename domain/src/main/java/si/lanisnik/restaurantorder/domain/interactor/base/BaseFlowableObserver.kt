package si.lanisnik.restaurantorder.domain.interactor.base

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * Default [SingleObserver] base class to define default response handling.
 */
open class BaseFlowableObserver<T> : SingleObserver<T> {

    override fun onSubscribe(d: Disposable) {}

    override fun onSuccess(result: T) {}

    override fun onError(exception: Throwable) {}
}
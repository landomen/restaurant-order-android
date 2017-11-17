package si.lanisnik.restaurantorder.ui.base.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
open class ShoppingCartViewModel(protected val shoppingCart: ShoppingCart) : ViewModel() {

    private val shoppingCartObservable: MutableLiveData<Int> = MutableLiveData()
    private lateinit var disposable: Disposable

    protected fun initShoppingCart() {
        disposable = shoppingCart.totalCountSubject
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    shoppingCartObservable.postValue(it)
                }
    }

    fun getShoppingCartObservable(): LiveData<Int> = shoppingCartObservable

    override fun onCleared() {
        if (!disposable.isDisposed)
            disposable.dispose()
        super.onCleared()
    }

}
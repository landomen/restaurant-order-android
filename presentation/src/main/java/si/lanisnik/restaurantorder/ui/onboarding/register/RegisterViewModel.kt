package si.lanisnik.restaurantorder.ui.onboarding.register

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import si.lanisnik.restaurantorder.domain.interactor.customer.RegisterCustomer
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
class RegisterViewModel @Inject constructor(private val registerCustomer: RegisterCustomer) : ViewModel() {

    private val liveData: MutableLiveData<SimpleResource> = MutableLiveData()

    override fun onCleared() {
        registerCustomer.unsubscribe()
        super.onCleared()
    }

    fun getObservable(): LiveData<SimpleResource> = liveData

    fun register(firstName: String, lastName: String, address: String, email: String, phoneNumber: String, password: String) {
        
    }

}
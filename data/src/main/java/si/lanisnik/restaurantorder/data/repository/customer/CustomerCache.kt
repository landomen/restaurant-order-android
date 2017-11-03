package si.lanisnik.restaurantorder.data.repository.customer

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.model.customer.Customer

/**
 * Created by Domen Lanišnik on 02/11/2017.
 * domen.lanisnik@gmail.com
 */
interface CustomerCache {

    fun getCustomer(): Single<Customer>

    fun isValid(): Single<Boolean>

    fun saveCustomer(customer: Customer): Completable

}
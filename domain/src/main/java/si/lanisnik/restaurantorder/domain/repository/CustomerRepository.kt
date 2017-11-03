package si.lanisnik.restaurantorder.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.model.customer.Customer

/**
 * Created by Domen Lanišnik on 22/09/2017.
 * domen.lanisnik@gmail.com
 */
interface CustomerRepository {

    fun login(email: String, password: String): Completable

    fun register(customer: Customer): Completable

    fun getCustomer(): Single<Customer>
}
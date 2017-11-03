package si.lanisnik.restaurantorder.data.repository.customer

import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.model.customer.Customer

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
interface CustomerRemote {

    fun login(email: String, password: String): Single<Customer>

    fun register(customer: Customer): Single<Customer>

    fun getCustomer(): Single<Customer>

    fun createCredentials(email: String, password: String): String

}
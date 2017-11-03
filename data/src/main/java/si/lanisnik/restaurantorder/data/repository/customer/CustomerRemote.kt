package si.lanisnik.restaurantorder.data.repository.customer

import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.customer.CustomerEntity

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
interface CustomerRemote {

    fun login(email: String, password: String): Single<CustomerEntity>

    fun register(customer: CustomerEntity): Single<CustomerEntity>

    fun getCustomer(): Single<CustomerEntity>

    fun createCredentials(email: String, password: String): String

}
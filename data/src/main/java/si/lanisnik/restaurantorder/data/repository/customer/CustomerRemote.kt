package si.lanisnik.restaurantorder.data.repository.customer

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.customer.CustomerEntity

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
interface CustomerRemote {

    fun login(email: String, password: String): Single<CustomerEntity>

    fun register(customer: CustomerEntity): Single<CustomerEntity>

    fun resetPassword(email: String): Completable

    fun changePassword(currentPassword: String, newPassword: String): Completable

    fun updateCustomer(customer: CustomerEntity): Single<CustomerEntity>

    fun getCustomer(): Single<CustomerEntity>

}
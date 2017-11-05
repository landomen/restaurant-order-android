package si.lanisnik.restaurantorder.data.repository.customer

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.customer.CustomerEntity

/**
 * Created by Domen Lanišnik on 02/11/2017.
 * domen.lanisnik@gmail.com
 */
interface CustomerCache {

    fun getCustomer(): Single<CustomerEntity>

    fun isValid(): Single<Boolean>

    fun isCached(): Single<Boolean>

    fun saveCustomer(customer: CustomerEntity): Completable

}
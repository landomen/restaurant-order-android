package si.lanisnik.restaurantorder.cache

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.cache.mapper.customer.CustomerCacheMapper
import si.lanisnik.restaurantorder.cache.util.findFirst
import si.lanisnik.restaurantorder.cache.util.getRealm
import si.lanisnik.restaurantorder.cache.util.transaction
import si.lanisnik.restaurantorder.data.entity.customer.CustomerEntity
import si.lanisnik.restaurantorder.data.repository.customer.CustomerCache
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 03/11/2017.
 * domen.lanisnik@gmail.com
 */
class CustomerCacheImpl @Inject constructor(private val mapper: CustomerCacheMapper) : CustomerCache {

    override fun getCustomer(): Single<CustomerEntity> {
        return Single.defer {
            val customer = getRealm().use {
                mapper.mapFromCached(it.findFirst()!!)
            }
            Single.just(customer)
        }
    }

    override fun isValid(): Single<Boolean> {
        // TODO
        return Single.just(true)
    }

    override fun saveCustomer(customer: CustomerEntity): Completable {
        return Completable.defer {
            getRealm().transaction {
                it.insertOrUpdate(mapper.mapToCached(customer))
            }
            Completable.complete()
        }
    }

}
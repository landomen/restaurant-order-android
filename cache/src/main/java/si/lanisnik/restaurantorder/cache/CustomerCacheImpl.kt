package si.lanisnik.restaurantorder.cache

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.cache.mapper.customer.CustomerCacheMapper
import si.lanisnik.restaurantorder.cache.model.customer.CachedCustomer
import si.lanisnik.restaurantorder.cache.preferences.SimpleStorage
import si.lanisnik.restaurantorder.cache.preferences.keys.LongKey
import si.lanisnik.restaurantorder.cache.util.*
import si.lanisnik.restaurantorder.data.entity.customer.CustomerEntity
import si.lanisnik.restaurantorder.data.repository.customer.CustomerCache
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 03/11/2017.
 * domen.lanisnik@gmail.com
 */
class CustomerCacheImpl @Inject constructor(private val mapper: CustomerCacheMapper,
                                            private val simpleStorage: SimpleStorage) : CustomerCache {

    override fun getCustomer(): Single<CustomerEntity> {
        return Single.defer {
            val customer = getRealm().use {
                mapper.mapFromCached(it.findFirst()!!)
            }
            Single.just(customer)
        }
    }

    override fun isCachedAndValid(): Single<Boolean> {
        return Single.defer {
            Single.just(getRealm().contains<CachedCustomer>() &&
                    !isCacheExpired(simpleStorage.getLong(LongKey.LAST_CACHE_TIME_CUSTOMER)))
        }
    }

    override fun saveCustomer(customer: CustomerEntity): Completable {
        return Completable.defer {
            getRealm().transaction {
                it.insertOrUpdate(mapper.mapToCached(customer))
            }
            setLastCacheTime(System.currentTimeMillis())
            Completable.complete()
        }
    }

    override fun hasCustomer(): Single<Boolean> {
        return Single.defer {
            Single.just(getRealm().contains<CachedCustomer>())
        }
    }

    private fun setLastCacheTime(time: Long) {
        simpleStorage.putLong(LongKey.LAST_CACHE_TIME_CUSTOMER, time)
    }

}
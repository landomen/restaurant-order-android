package si.lanisnik.restaurantorder.cache.mapper.customer

import si.lanisnik.restaurantorder.cache.mapper.CacheEntityMapper
import si.lanisnik.restaurantorder.cache.model.customer.CachedCustomer
import si.lanisnik.restaurantorder.data.entity.customer.CustomerEntity
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 03/11/2017.
 * domen.lanisnik@gmail.com
 */
class CustomerCacheMapper @Inject constructor() : CacheEntityMapper<CachedCustomer, CustomerEntity> {

    override fun mapFromCached(cachedModel: CachedCustomer): CustomerEntity {
        return CustomerEntity(
                cachedModel.id,
                cachedModel.email,
                cachedModel.password,
                cachedModel.firstName,
                cachedModel.lastName,
                cachedModel.phoneNumber,
                cachedModel.address
        )
    }

    override fun mapToCached(dataModel: CustomerEntity): CachedCustomer {
        return CachedCustomer(
                dataModel.id,
                dataModel.email,
                dataModel.password,
                dataModel.firstName,
                dataModel.lastName,
                dataModel.phoneNumber,
                dataModel.address
        )
    }
}
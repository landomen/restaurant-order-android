package si.lanisnik.restaurantorder.data

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.mapper.AddressEntityMapper
import si.lanisnik.restaurantorder.data.repository.address.AddressRemote
import si.lanisnik.restaurantorder.domain.model.address.DeliveryAddress
import si.lanisnik.restaurantorder.domain.repository.AddressRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
class AddressDataRepository @Inject constructor(private val remote: AddressRemote,
                                                private val mapper: AddressEntityMapper) : AddressRepository {

    override fun getAddresses(): Single<List<DeliveryAddress>> {
        return remote.getAddresses()
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun addAddress(address: DeliveryAddress): Single<DeliveryAddress> {
        return remote.addAddress(mapper.mapToEntity(address))
                .map { mapper.mapFromEntity(it) }
    }

    override fun deleteAddress(addressId: Int): Completable {
        return remote.deleteAddress(addressId)
    }

    override fun selectAddress(addressId: Int): Completable {
        return remote.selectAddress(addressId)
    }
}
package si.lanisnik.restaurantorder.remote.address

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.address.AddressEntity
import si.lanisnik.restaurantorder.data.repository.address.AddressRemote
import si.lanisnik.restaurantorder.remote.address.mapper.AddressRemoteMapper
import si.lanisnik.restaurantorder.remote.address.model.AddressRequest
import si.lanisnik.restaurantorder.remote.address.service.AddressService
import si.lanisnik.restaurantorder.remote.base.RemoteExceptionMapper
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 06/11/2017.
 * domen.lanisnik@gmail.com
 */
class AddressRemoteImpl @Inject constructor(private val service: AddressService,
                                            private val mapper: AddressRemoteMapper,
                                            private val exceptionMapper: RemoteExceptionMapper) : AddressRemote {

    override fun getAddresses(): Single<List<AddressEntity>> {
        return service.getAddresses()
                .map { it.addresses }
                .map {
                    it.map { mapper.mapFromRemote(it) }
                }
    }

    override fun addAddress(address: AddressEntity): Single<AddressEntity> {
        val request = AddressRequest(address.street, address.number, address.city, address.postcode, address.note, address.selected)
        return service.addAddress(request)
                .map { mapper.mapFromRemote(it) }
    }

    override fun deleteAddress(addressId: Int): Completable {
        return service.deleteAddress(addressId)
                .onErrorResumeNext {
                    Completable.error(exceptionMapper.mapException(it))
                }
    }

    override fun selectAddress(addressId: Int): Completable {
        return service.selectAddress(addressId)
    }

}
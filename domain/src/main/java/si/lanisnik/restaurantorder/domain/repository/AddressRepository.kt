package si.lanisnik.restaurantorder.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.model.address.DeliveryAddress

/**
 * Created by Domen Lanišnik on 06/11/2017.
 * domen.lanisnik@gmail.com
 */
interface AddressRepository {

    fun getAddresses(): Single<List<DeliveryAddress>>

    fun addAddress(address: DeliveryAddress): Single<DeliveryAddress>

    fun deleteAddress(addressId: Int): Completable

    fun selectAddress(addressId: Int): Completable
}
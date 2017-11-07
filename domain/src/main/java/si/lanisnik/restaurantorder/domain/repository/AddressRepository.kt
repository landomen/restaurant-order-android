package si.lanisnik.restaurantorder.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.model.address.Address

/**
 * Created by Domen Lanišnik on 06/11/2017.
 * domen.lanisnik@gmail.com
 */
interface AddressRepository {

    fun getAddresses(): Single<List<Address>>

    fun addAddress(address: Address): Single<Address>

    fun updateAddress(address: Address): Single<Address>

    fun deleteAddress(addressId: Int): Completable

    fun selectAddress(addressId: Int): Completable
}
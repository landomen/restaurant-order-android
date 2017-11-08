package si.lanisnik.restaurantorder.data.repository.address

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.address.AddressEntity

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
interface AddressRemote {

    fun getAddresses(): Single<List<AddressEntity>>

    fun addAddress(address: AddressEntity): Single<AddressEntity>

    fun deleteAddress(addressId: Int): Completable

    fun selectAddress(addressId: Int): Completable

}
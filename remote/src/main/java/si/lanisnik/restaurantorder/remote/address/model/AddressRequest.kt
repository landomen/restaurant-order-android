package si.lanisnik.restaurantorder.remote.address.model

/**
 * Created by Domen Lanišnik on 06/11/2017.
 * domen.lanisnik@gmail.com
 */
data class AddressRequest(
        val street: String,
        val number: String,
        val city: String,
        val postcode: Int,
        val note: String?,
        val selected: Boolean = false
)
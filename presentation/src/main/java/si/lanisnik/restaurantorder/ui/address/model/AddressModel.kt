package si.lanisnik.restaurantorder.ui.address.model

/**
 * Created by Domen Lanišnik on 07/11/2017.
 * domen.lanisnik@gmail.com
 */
data class AddressModel(
        val id: Int = 0,
        val street: String = "",
        val number: String = "",
        val city: String = "",
        val postcode: Int = 0,
        val note: String? = null,
        val selected: Boolean = false,
        val deleted: Boolean = false,
        val customerId: Int = 0) {

    fun getAddress(): String = "$street $number, $postcode $city"
}
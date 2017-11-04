package si.lanisnik.restaurantorder.ui.customer.model

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
data class CustomerModel(
        val id: Int,
        val email: String,
        val password: String? = null,
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val address: String
)
package si.lanisnik.restaurantorder.remote.model.customer

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class CustomerDto(
        val id: Int,
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val address: String
)
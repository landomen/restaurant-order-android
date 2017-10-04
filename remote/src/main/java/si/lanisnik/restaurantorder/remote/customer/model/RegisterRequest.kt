package si.lanisnik.restaurantorder.remote.customer.model

/**
 * Created by Domen Lanišnik on 03/09/2017.
 * domen.lanisnik@gmail.com
 */
data class RegisterRequest(
        val firstName: String,
        val lastName: String,
        val email: String,
        val phoneNumber: String,
        val address: String,
        val password: String
)
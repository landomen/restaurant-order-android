package si.lanisnik.restaurantorder.remote.customer.model

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
data class UpdateProfileRequest(
        val firstName: String,
        val lastName: String,
        val email: String,
        val phoneNumber: String
)
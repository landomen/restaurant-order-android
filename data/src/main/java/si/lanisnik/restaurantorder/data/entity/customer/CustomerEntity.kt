package si.lanisnik.restaurantorder.data.entity.customer

/**
 * Created by Domen Lanišnik on 02/11/2017.
 * domen.lanisnik@gmail.com
 */
data class CustomerEntity(
        val id: Int,
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val address: String
)
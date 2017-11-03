package si.lanisnik.restaurantorder.data.component

/**
 * Created by Domen Lanišnik on 02/11/2017.
 * domen.lanisnik@gmail.com
 */
interface AuthorizationComponent {

    fun saveAuthorization(credentials: String)

    fun getAuthorization(): String

}
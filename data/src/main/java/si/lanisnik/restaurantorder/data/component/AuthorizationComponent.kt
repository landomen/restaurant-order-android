package si.lanisnik.restaurantorder.data.component

/**
 * Created by Domen Lanišnik on 02/11/2017.
 * domen.lanisnik@gmail.com
 */
interface AuthorizationComponent {

    fun saveUsername(username: String)

    fun savePassword(password: String)

    fun getUsername(): String

    fun getPassword(): String

}
package si.lanisnik.restaurantorder.cache.component

import si.lanisnik.restaurantorder.cache.preferences.SimpleStorage
import si.lanisnik.restaurantorder.cache.preferences.keys.StringKey
import si.lanisnik.restaurantorder.data.component.AuthorizationComponent
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 02/11/2017.
 * domen.lanisnik@gmail.com
 */
class AuthorizationComponentImpl @Inject constructor(private val simpleStorage: SimpleStorage) : AuthorizationComponent {

    override fun saveAuthorization(credentials: String) {
        simpleStorage.putString(StringKey.CREDENTIALS, credentials)
    }

    override fun getAuthorization(): String = simpleStorage.getString(StringKey.CREDENTIALS) ?: ""
}
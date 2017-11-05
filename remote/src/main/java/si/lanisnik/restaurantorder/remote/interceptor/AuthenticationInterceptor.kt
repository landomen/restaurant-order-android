package si.lanisnik.restaurantorder.remote.interceptor

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import si.lanisnik.restaurantorder.data.component.AuthorizationComponent
import si.lanisnik.restaurantorder.remote.NetConstants
import javax.inject.Inject

/**
 * Intercepts an outgoing network call and adds authorization header if needed.
 *
 * Created by Domen Lanišnik on 30/10/2017.
 * domen.lanisnik@gmail.com
 */
class AuthenticationInterceptor @Inject constructor(private val authorizationComponent: AuthorizationComponent) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        // automatically add authorization header to every request that demands it
        if (original.header(NetConstants.HEADER_AUTHORIZATION)?.contains(NetConstants.REPLACE_CHAR) == true) {
            requestBuilder.header(NetConstants.HEADER_AUTHORIZATION, buildAuthorization())
        }
        return chain.proceed(requestBuilder.build())
    }

    private fun buildAuthorization(): String {
        return Credentials.basic(authorizationComponent.getUsername(), authorizationComponent.getPassword())
    }

}
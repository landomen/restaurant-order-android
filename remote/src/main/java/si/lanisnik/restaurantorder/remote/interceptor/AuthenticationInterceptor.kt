package si.lanisnik.restaurantorder.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import si.lanisnik.restaurantorder.remote.NetConstants
import javax.inject.Inject

/**
 * Intercepts an outgoing network call and adds authorization header if needed.
 *
 * Created by Domen Lanišnik on 30/10/2017.
 * domen.lanisnik@gmail.com
 */
class AuthenticationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        // automatically add authorization header to every request that demands it
        if (original.header(NetConstants.HEADER_AUTHORIZATION)?.contains(NetConstants.REPLACE_CHAR) == true) {
            // TODO Add authorization header value
//                requestBuilder.header(NetConstants.HEADER_AUTHORIZATION, "")
        }
        return chain.proceed(requestBuilder.build())
    }

}
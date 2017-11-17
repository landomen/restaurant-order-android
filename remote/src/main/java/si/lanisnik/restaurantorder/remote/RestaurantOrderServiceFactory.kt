package si.lanisnik.restaurantorder.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import si.lanisnik.restaurantorder.remote.interceptor.AuthenticationInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Provides "make" methods to create instances of different services
 * which define the Rest API endpoints.
 */
class RestaurantOrderServiceFactory @Inject constructor(baseUrl: String,
                                                        private val loggingEnabled: Boolean,
                                                        private val authenticationInterceptor: AuthenticationInterceptor) {

    private val retrofit: Retrofit

    init {
        retrofit = makeRetrofit(baseUrl)
    }

    /**
     * @param <S> API endpoint interface
     */
    fun <S> makeService(service: Class<S>): S = retrofit.create(service)

    private fun makeRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(makeGsonConverterFactory())
            .client(makeOkHttpClient())
            .build()

    private fun makeGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    private fun makeOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .readTimeout(NetConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .writeTimeout(NetConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .connectTimeout(NetConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .addInterceptor(authenticationInterceptor)
                    .addInterceptor(makeLoggingInterceptor()) // order of interceptors is important
                    .build()

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (loggingEnabled)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }

}
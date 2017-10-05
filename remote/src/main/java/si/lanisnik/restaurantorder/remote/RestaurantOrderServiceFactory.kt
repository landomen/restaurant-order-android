package si.lanisnik.restaurantorder.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provides "make" methods to create instances of different services
 * which define the Rest API endpoints.
 */
object RestaurantOrderServiceFactory {

    private val retrofit: Retrofit

    init {
        retrofit = makeRetrofit()
    }

    /**
     * @param <S> API endpoint interface
     */
    fun <S> makeService(service: Class<S>): S = retrofit.create(service)

    private fun makeRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("http://d59574a3.ngrok.io/")
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
                    .addInterceptor(makeLoggingInterceptor())
                    .addInterceptor(makeRequestInterceptor())
                    .build()

    private fun makeRequestInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        // automatically add authorization header to every request that demands it
        if (original.header(NetConstants.HEADER_AUTHORIZATION)?.contains(NetConstants.REPLACE_CHAR) == true) {
            // TODO Add authorization header value
//                requestBuilder.header(NetConstants.HEADER_AUTHORIZATION, "")
        }
        chain.proceed(requestBuilder.build())
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (true) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

}
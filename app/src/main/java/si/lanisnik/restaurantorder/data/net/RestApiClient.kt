package si.lanisnik.restaurantorder.data.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import si.lanisnik.restaurantorder.BuildConfig
import si.lanisnik.restaurantorder.data.net.apis.RestApiCustomer
import si.lanisnik.restaurantorder.data.net.apis.RestApiMenuItems
import java.util.concurrent.TimeUnit

/**
 * Created by Domen Lanišnik on 03/09/2017.
 * domen.lanisnik@gmail.com
 */
object RestApiClient {

    val api: RestApiCustomer
    val menuItemsApi: RestApiMenuItems

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build()
        api = retrofit.create(RestApiCustomer::class.java)
        menuItemsApi = retrofit.create(RestApiMenuItems::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.readTimeout(NetConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(NetConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(NetConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)

        okHttpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()

            // automatically add authorization header to every request that demands it
            if (original.header(NetConstants.HEADER_AUTHORIZATION)?.contains(NetConstants.REPLACE_CHAR) == true) {
                // TODO Add authorization header value
//                requestBuilder.header(NetConstants.HEADER_AUTHORIZATION, "")
            }

            chain.proceed(requestBuilder.build())
        }

        // enable response logging
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        return okHttpClient.build()
    }

}
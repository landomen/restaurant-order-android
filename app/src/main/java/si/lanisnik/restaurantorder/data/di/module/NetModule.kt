package si.lanisnik.restaurantorder.data.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import si.lanisnik.restaurantorder.BuildConfig
import si.lanisnik.restaurantorder.data.net.NetConstants
import si.lanisnik.restaurantorder.data.net.apis.RestApiCustomer
import si.lanisnik.restaurantorder.data.net.apis.RestApiMenuItems
import si.lanisnik.restaurantorder.data.net.apis.RestApiOrders
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Domen Lanišnik on 16/09/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class NetModule(private val serverUrl: String) {

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
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

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverter: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(serverUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverter)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideCustomerApi(retrofit: Retrofit): RestApiCustomer =
            retrofit.create(RestApiCustomer::class.java)

    @Provides
    @Singleton
    fun provideOrdersApi(retrofit: Retrofit): RestApiOrders =
            retrofit.create(RestApiOrders::class.java)

    @Provides
    @Singleton
    fun provideMenuItemsApi(retrofit: Retrofit): RestApiMenuItems =
            retrofit.create(RestApiMenuItems::class.java)
}
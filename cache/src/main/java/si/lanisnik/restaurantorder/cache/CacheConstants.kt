package si.lanisnik.restaurantorder.cache

/**
 * Created by Domen Lanišnik on 02/10/2017.
 * domen.lanisnik@gmail.com
 */
object CacheConstants {
    /**
     * How long the cached data is valid.
     */
    const val EXPIRATION_TIME = (24 * 3600 * 1000).toLong() // 1 day
}
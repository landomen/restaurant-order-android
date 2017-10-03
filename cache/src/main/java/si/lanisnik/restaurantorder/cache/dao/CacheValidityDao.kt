package si.lanisnik.restaurantorder.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import si.lanisnik.restaurantorder.cache.db.constants.DatabaseConstants
import si.lanisnik.restaurantorder.cache.model.validity.CacheValidity
import si.lanisnik.restaurantorder.cache.model.validity.CachedDataType

/**
 * Created by Domen Lanišnik on 02/10/2017.
 * domen.lanisnik@gmail.com
 */
@Dao
interface CacheValidityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertValidity(cacheValidity: CacheValidity)

    @Query("SELECT lastFetch FROM ${DatabaseConstants.TABLE_CACHE_VALIDITY} WHERE type = :type")
    fun getValidity(type: CachedDataType): Long

}
package si.lanisnik.restaurantorder.cache.model.validity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import si.lanisnik.restaurantorder.cache.db.constants.DatabaseConstants

/**
 * Created by Domen Lanišnik on 02/10/2017.
 * domen.lanisnik@gmail.com
 */
@Entity(tableName = DatabaseConstants.TABLE_CACHE_VALIDITY)
data class CacheValidity(
        @PrimaryKey
        var type: CachedDataType,
        var lastFetch: Long
)
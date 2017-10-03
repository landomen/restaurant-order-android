package si.lanisnik.restaurantorder.cache.db.converters

import android.arch.persistence.room.TypeConverter
import si.lanisnik.restaurantorder.cache.model.validity.CachedDataType

/**
 * Created by Domen Lanišnik on 03/10/2017.
 * domen.lanisnik@gmail.com
 */
class RoomTypeConverters {

    @TypeConverter
    fun fromCachedDataType(cachedDataType: CachedDataType): Int = cachedDataType.id

    @TypeConverter
    fun toCachedDataType(typeId: Int): CachedDataType = CachedDataType.fromId(typeId)

}
package si.lanisnik.restaurantorder.cache.mapper

/**
 * Defines methods for mapping cached models into data entity models used in data layer.
 *
 * @param <C> the cached model input type
 * @param <E> the data entity model output type
 */
interface CacheEntityMapper<C, E> {

    fun mapFromCached(cachedModel: C): E

    fun mapToCached(dataModel: E): C
}
package si.lanisnik.restaurantorder.data.mapper

/**
 * Defines methods for mapping domain models into data entity models used in data layer.
 *
 * @param <E> the data entity model
 * @param <D> the domain model
 */
interface EntityMapper<E, D> {

    fun mapFromEntity(model: E): D

    fun mapToEntity(model: D): E
}
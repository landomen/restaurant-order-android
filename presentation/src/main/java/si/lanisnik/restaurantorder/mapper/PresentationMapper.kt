package si.lanisnik.restaurantorder.mapper

/**
 * Maps from domain layer model to presentation layer model.
 *
 * @param <D> domain model input
 * @param <M> presentation model output
 */
interface PresentationMapper<in D, out M> {
    fun mapToModel(model: D): M
}
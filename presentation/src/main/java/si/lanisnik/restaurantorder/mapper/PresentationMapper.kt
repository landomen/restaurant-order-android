package si.lanisnik.restaurantorder.mapper

/**
 * Maps from domain layer model to presentation layer model.
 *
 * @param <D> domain model input
 * @param <M> presentation model output
 */
interface PresentationMapper<D, M> {
    fun mapToModel(model: D): M

    /**
     * Implement only if you need reverse mapping.
     */
    fun mapFromModel(model: M): D = throw UnsupportedOperationException()
}
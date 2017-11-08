package si.lanisnik.restaurantorder.remote.base.mapper

/**
 * Defines methods for mapping remote models into data entity models used in data layer.
 *
 * @param <R> the remote model input type
 * @param <E> the data entity model output type
 */
interface RemoteEntityMapper<R, E> {

    fun mapFromRemote(remoteModel: R): E

    fun mapToRemote(entity: E): R = throw UnsupportedOperationException()
}
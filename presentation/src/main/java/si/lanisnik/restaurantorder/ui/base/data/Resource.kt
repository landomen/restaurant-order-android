package si.lanisnik.restaurantorder.ui.base.data

/**
 * Describes data with a status.
 */
class Resource<out T> private constructor(val status: ResourceState, val data: T? = null) {

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(ResourceState.SUCCESS, data)

        fun <T> error(): Resource<T> = Resource(ResourceState.ERROR)

        fun <T> loading(): Resource<T> = Resource(ResourceState.LOADING)
    }
}
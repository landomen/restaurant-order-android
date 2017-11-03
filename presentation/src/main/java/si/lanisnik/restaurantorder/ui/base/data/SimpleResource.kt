package si.lanisnik.restaurantorder.ui.base.data

/**
 * Used for actions in which we are not interested in data, but in the state and error message.
 */
class SimpleResource private constructor(val status: ResourceState, val errorMessage: Int? = null) {

    companion object {
        fun success(): SimpleResource = SimpleResource(ResourceState.SUCCESS)

        fun error(errorMessage: Int): SimpleResource = SimpleResource(ResourceState.ERROR, errorMessage)

        fun loading(): SimpleResource = SimpleResource(ResourceState.LOADING)
    }

}
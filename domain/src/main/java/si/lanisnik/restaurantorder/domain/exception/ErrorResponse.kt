package si.lanisnik.restaurantorder.domain.exception

/**
 * Interface to represent a wrapper around an [Exception] to manage errors.
 */
interface ErrorResponse {
    fun getException(): Exception
    fun getErrorMessage(): String
}
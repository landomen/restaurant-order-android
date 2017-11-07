package si.lanisnik.restaurantorder.remote.base

import retrofit2.HttpException
import si.lanisnik.restaurantorder.domain.exception.ConflictException
import si.lanisnik.restaurantorder.domain.exception.ForbiddenException
import si.lanisnik.restaurantorder.domain.exception.NotAuthorizedException
import si.lanisnik.restaurantorder.domain.exception.NotFoundException
import si.lanisnik.restaurantorder.remote.HttpStatus
import javax.inject.Inject

/**
 * Maps remote exceptions to their domain representations.
 *
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
class RemoteExceptionMapper @Inject constructor() {

    fun mapException(t: Throwable): Throwable {
        if (t is HttpException) {
            return when (t.code()) {
                HttpStatus.UNAUTHORIZED -> NotAuthorizedException()
                HttpStatus.FORBIDDEN -> ForbiddenException()
                HttpStatus.NOT_FOUND -> NotFoundException()
                HttpStatus.CONFLICT -> ConflictException()
                else -> t
            }
        }
        return t
    }

}
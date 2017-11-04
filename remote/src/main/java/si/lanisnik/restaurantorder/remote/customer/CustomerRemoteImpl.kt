package si.lanisnik.restaurantorder.remote.customer

import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.Credentials
import si.lanisnik.restaurantorder.data.entity.customer.CustomerEntity
import si.lanisnik.restaurantorder.data.repository.customer.CustomerRemote
import si.lanisnik.restaurantorder.remote.base.RemoteExceptionMapper
import si.lanisnik.restaurantorder.remote.customer.mapper.CustomerRemoteMapper
import si.lanisnik.restaurantorder.remote.customer.model.ChangePasswordRequest
import si.lanisnik.restaurantorder.remote.customer.model.LoginRequest
import si.lanisnik.restaurantorder.remote.customer.model.RegisterRequest
import si.lanisnik.restaurantorder.remote.customer.model.ResetPasswordRequest
import si.lanisnik.restaurantorder.remote.customer.service.CustomerService
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class CustomerRemoteImpl @Inject constructor(private val service: CustomerService,
                                             private val customerMapper: CustomerRemoteMapper,
                                             private val exceptionMapper: RemoteExceptionMapper) : CustomerRemote {

    override fun login(email: String, password: String): Single<CustomerEntity> {
        return service.login(LoginRequest(email, password))
                .onErrorResumeNext {
                    Single.error(exceptionMapper.mapException(it))
                }
                .map {
                    customerMapper.mapFromRemote(it)
                }
    }

    override fun register(customer: CustomerEntity): Single<CustomerEntity> {
        val request = RegisterRequest(customer.firstName, customer.lastName, customer.email, customer.phoneNumber, customer.password!!)
        return service.register(request)
                .onErrorResumeNext {
                    Single.error(exceptionMapper.mapException(it))
                }
                .map {
                    customerMapper.mapFromRemote(it)
                }
    }

    override fun resetPassword(email: String): Completable =
            service.resetPassword(ResetPasswordRequest(email))
                    .onErrorResumeNext {
                        Completable.error(exceptionMapper.mapException(it))
                    }

    override fun changePassword(currentPassword: String, newPassword: String): Completable =
            service.changePassword(ChangePasswordRequest(currentPassword, newPassword))
                    .onErrorResumeNext {
                        Completable.error(exceptionMapper.mapException(it))
                    }

    override fun getCustomer(): Single<CustomerEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createCredentials(email: String, password: String): String =
            Credentials.basic(email, password)
}
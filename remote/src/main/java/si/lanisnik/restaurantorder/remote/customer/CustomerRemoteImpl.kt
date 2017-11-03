package si.lanisnik.restaurantorder.remote.customer

import io.reactivex.Single
import okhttp3.Credentials
import si.lanisnik.restaurantorder.data.repository.customer.CustomerRemote
import si.lanisnik.restaurantorder.domain.model.customer.Customer
import si.lanisnik.restaurantorder.remote.customer.mapper.CustomerRemoteMapper
import si.lanisnik.restaurantorder.remote.customer.model.LoginRequest
import si.lanisnik.restaurantorder.remote.customer.service.CustomerService
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class CustomerRemoteImpl @Inject constructor(private val service: CustomerService,
                                             private val customerMapper: CustomerRemoteMapper) : CustomerRemote {

    override fun login(email: String, password: String): Single<Customer> {
        return service.login(LoginRequest(email, password))
                .map { customerMapper.mapFromRemote(it) }
    }

    override fun register(customer: Customer): Single<Customer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCustomer(): Single<Customer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createCredentials(email: String, password: String): String =
            Credentials.basic(email, password)
}
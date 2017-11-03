package si.lanisnik.restaurantorder.data

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.component.AuthorizationComponent
import si.lanisnik.restaurantorder.data.repository.customer.CustomerCache
import si.lanisnik.restaurantorder.data.repository.customer.CustomerRemote
import si.lanisnik.restaurantorder.domain.model.customer.Customer
import si.lanisnik.restaurantorder.domain.repository.CustomerRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 02/11/2017.
 * domen.lanisnik@gmail.com
 */
class CustomerDataRepository @Inject constructor(private val cache: CustomerCache,
                                                 private val remote: CustomerRemote,
                                                 private val authorizationComponent: AuthorizationComponent) : CustomerRepository {

    override fun login(email: String, password: String): Completable {
        return remote.login(email, password)
                .flatMapCompletable {
                    cache.saveCustomer(it)
                }.doOnComplete {
            saveCredentials(email, password)
        }
    }

    override fun register(customer: Customer): Completable {
        return remote.register(customer)
                .flatMapCompletable {
                    cache.saveCustomer(it)
                }.doOnComplete {
            saveCredentials(customer.email, customer.password)
        }
    }

    override fun getCustomer(): Single<Customer> {
        return cache.isValid().flatMap { cacheValid ->
            // check if cache is still valid (and available)
            if (!cacheValid) {
                remote.getCustomer().doOnSuccess {
                    // save to cache
                    cache.saveCustomer(it)
                }.onErrorResumeNext {
                    // use cache if call fails
                    cache.getCustomer()
                }
            } else {
                cache.getCustomer()
            }
        }
    }

    private fun saveCredentials(email: String, password: String) {
        authorizationComponent.saveAuthorization(remote.createCredentials(email, password))
    }

}
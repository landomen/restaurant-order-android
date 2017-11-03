package si.lanisnik.restaurantorder.domain.interactor.customer

import io.reactivex.Completable
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.CompletableUseCase
import si.lanisnik.restaurantorder.domain.repository.CustomerRepository
import javax.inject.Inject

/**
 * Performs the login procedure.
 *
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class LoginCustomer @Inject constructor(private val customerRepository: CustomerRepository,
                                        threadExecutor: JobExecutionThread,
                                        postExecutionThread: PostExecutionThread) :
        CompletableUseCase<LoginCustomer.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Completable {
        checkNotNull(params)
        return customerRepository.login(params!!.email, params.password)
    }
    
    data class Params(val email: String, val password: String)
}
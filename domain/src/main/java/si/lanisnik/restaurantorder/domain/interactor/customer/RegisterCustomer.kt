package si.lanisnik.restaurantorder.domain.interactor.customer

import io.reactivex.Completable
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.CompletableUseCase
import si.lanisnik.restaurantorder.domain.model.customer.Customer
import si.lanisnik.restaurantorder.domain.repository.CustomerRepository
import javax.inject.Inject

/**
 * Performs the registration procedure for new customer.
 *
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
class RegisterCustomer @Inject constructor(private val customerRepository: CustomerRepository,
                                           threadExecutor: JobExecutionThread,
                                           postExecutionThread: PostExecutionThread) :
        CompletableUseCase<Customer>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Customer): Completable =
            customerRepository.register(params)

}
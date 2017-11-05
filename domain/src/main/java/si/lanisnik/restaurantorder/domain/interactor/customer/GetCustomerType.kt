package si.lanisnik.restaurantorder.domain.interactor.customer

import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.SingleUseCase
import si.lanisnik.restaurantorder.domain.model.customer.CustomerType
import si.lanisnik.restaurantorder.domain.repository.CustomerRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class GetCustomerType @Inject constructor(private val customerRepository: CustomerRepository,
                                          threadExecutor: JobExecutionThread,
                                          postExecutionThread: PostExecutionThread) :
        SingleUseCase<CustomerType, Any?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Any?): Single<CustomerType> {
        return customerRepository.hasCustomer()
                .map {
                    if (it)
                        CustomerType.LOGGED_IN
                    else
                        CustomerType.ANONYMOUS
                }
    }

}
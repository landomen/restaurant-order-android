package si.lanisnik.restaurantorder.domain.interactor.customer

import io.reactivex.Completable
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.CompletableUseCase
import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart
import si.lanisnik.restaurantorder.domain.repository.CustomerRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 19/11/2017.
 * domen.lanisnik@gmail.com
 */
class LogoutCustomer @Inject constructor(private val customerRepository: CustomerRepository,
                                         private val shoppingCart: ShoppingCart,
                                         jobExecutionThread: JobExecutionThread,
                                         postExecutionThread: PostExecutionThread) :
        CompletableUseCase<Any?>(jobExecutionThread, postExecutionThread) {

    override fun buildUseCaseObservable(params: Any?): Completable {
        return customerRepository.logoutCustomer()
                .doOnComplete {
                    shoppingCart.clear()
                }
    }

}
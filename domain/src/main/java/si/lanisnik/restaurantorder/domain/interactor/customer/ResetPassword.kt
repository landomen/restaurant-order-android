package si.lanisnik.restaurantorder.domain.interactor.customer

import io.reactivex.Completable
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.CompletableUseCase
import si.lanisnik.restaurantorder.domain.repository.CustomerRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
class ResetPassword @Inject constructor(private val customerRepository: CustomerRepository,
                                        threadExecutor: JobExecutionThread,
                                        postExecutionThread: PostExecutionThread) :
        CompletableUseCase<ResetPassword.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Completable =
            customerRepository.resetPassword(params.email)

    data class Params(val email: String)
}
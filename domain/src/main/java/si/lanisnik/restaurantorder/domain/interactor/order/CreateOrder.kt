package si.lanisnik.restaurantorder.domain.interactor.order

import io.reactivex.Completable
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.CompletableUseCase
import si.lanisnik.restaurantorder.domain.repository.OrderRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 18/11/2017.
 * domen.lanisnik@gmail.com
 */
class CreateOrder @Inject constructor(private val shoppingCart: ShoppingCart,
                                      private val orderRepository: OrderRepository,
                                      jobExecutionThread: JobExecutionThread,
                                      postExecutionThread: PostExecutionThread) :
        CompletableUseCase<CreateOrder.Params>(jobExecutionThread, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Completable {
        return orderRepository.createOrder(params.selectedAddressId,
                shoppingCart.getSelectedMenuItems(), parseComment(params.comment))
    }

    private fun parseComment(comment: String): String? = if (comment.isNotEmpty()) comment else null

    data class Params(val selectedAddressId: Int, val comment: String)
}
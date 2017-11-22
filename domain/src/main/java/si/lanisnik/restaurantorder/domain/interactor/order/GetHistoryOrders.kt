package si.lanisnik.restaurantorder.domain.interactor.order

import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.SingleUseCase
import si.lanisnik.restaurantorder.domain.model.order.Order
import si.lanisnik.restaurantorder.domain.repository.OrderRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
class GetHistoryOrders @Inject constructor(private val orderRepository: OrderRepository,
                                           jobExecutionThread: JobExecutionThread,
                                           postExecutionThread: PostExecutionThread) :
        SingleUseCase<List<Order>, Any?>(jobExecutionThread, postExecutionThread) {

    override fun buildUseCaseObservable(params: Any?): Single<List<Order>> {
        return orderRepository.getOrdersHistory()
    }
}
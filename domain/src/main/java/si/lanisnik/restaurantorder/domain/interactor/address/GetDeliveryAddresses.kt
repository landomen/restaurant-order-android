package si.lanisnik.restaurantorder.domain.interactor.address

import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.SingleUseCase
import si.lanisnik.restaurantorder.domain.model.address.DeliveryAddress
import si.lanisnik.restaurantorder.domain.repository.AddressRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
class GetDeliveryAddresses @Inject constructor(private val addressRepository: AddressRepository,
                                               jobThreadExecutor: JobExecutionThread,
                                               postExecutionThread: PostExecutionThread) :
        SingleUseCase<List<DeliveryAddress>, Void>(jobThreadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Single<List<DeliveryAddress>> {
        return addressRepository.getAddresses()
    }

}
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
class AddDeliveryAddress @Inject constructor(private val addressRepository: AddressRepository,
                                             jobExecutionThread: JobExecutionThread,
                                             postExecutionThread: PostExecutionThread) :
        SingleUseCase<DeliveryAddress, DeliveryAddress>(jobExecutionThread, postExecutionThread) {

    override fun buildUseCaseObservable(params: DeliveryAddress?): Single<DeliveryAddress> {
        checkNotNull(params)
        return addressRepository.addAddress(params!!)
    }

}
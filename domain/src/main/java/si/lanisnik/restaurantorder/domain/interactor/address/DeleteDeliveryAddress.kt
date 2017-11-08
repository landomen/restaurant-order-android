package si.lanisnik.restaurantorder.domain.interactor.address

import io.reactivex.Completable
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.CompletableUseCase
import si.lanisnik.restaurantorder.domain.repository.AddressRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
class DeleteDeliveryAddress @Inject constructor(private val addressRepository: AddressRepository,
                                                jobExecutionThread: JobExecutionThread,
                                                postExecutionThread: PostExecutionThread) :
        CompletableUseCase<DeleteDeliveryAddress.Params>(jobExecutionThread, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Completable {
        return addressRepository.deleteAddress(params.addressId)
    }

    data class Params(val addressId: Int)
}
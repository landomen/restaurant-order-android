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
        SingleUseCase<DeliveryAddress, AddDeliveryAddress.Params>(jobExecutionThread, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Single<DeliveryAddress> {
        checkNotNull(params)
        return Single.create<DeliveryAddress> {
            val addressParts = params!!.address.split(",")
            if (addressParts.size >= 2) {
                val onlyNumbersRegex = "[^0-9]+".toRegex()
                val onlyCharsRegex = "[0-9]+".toRegex()

                val street = addressParts[0].replace(onlyCharsRegex, "").trim()
                val streetNumber = addressParts[0].replace(onlyNumbersRegex, "").trim().toIntOrNull() ?: 0
                val postcode = addressParts[1].replace(onlyNumbersRegex, "").trim().toIntOrNull() ?: 0
                val city = addressParts[1].replace(onlyCharsRegex, "").trim()

                val deliveryAddress = DeliveryAddress(street = street, number = streetNumber.toString(),
                        city = city, postcode = postcode, note = params.note, selected = params.selected)
                it.onSuccess(deliveryAddress)
            } else {
                it.onError(NumberFormatException())
            }
        }.flatMap {
            addressRepository.addAddress(it)
        }
    }

    data class Params(val address: String, val note: String?, val selected: Boolean)

}
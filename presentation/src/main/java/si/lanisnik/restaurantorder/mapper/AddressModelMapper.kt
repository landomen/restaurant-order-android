package si.lanisnik.restaurantorder.mapper

import si.lanisnik.restaurantorder.domain.model.address.DeliveryAddress
import si.lanisnik.restaurantorder.ui.address.model.AddressModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
class AddressModelMapper @Inject constructor() : PresentationMapper<DeliveryAddress, AddressModel> {

    override fun mapToModel(model: DeliveryAddress): AddressModel = AddressModel(
            model.id,
            model.street,
            model.number,
            model.city,
            model.postcode,
            model.note,
            model.selected,
            model.deleted,
            model.customerId
    )

}
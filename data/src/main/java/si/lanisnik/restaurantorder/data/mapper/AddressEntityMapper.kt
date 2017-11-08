package si.lanisnik.restaurantorder.data.mapper

import si.lanisnik.restaurantorder.data.entity.address.AddressEntity
import si.lanisnik.restaurantorder.domain.model.address.DeliveryAddress
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
class AddressEntityMapper @Inject constructor() : EntityMapper<AddressEntity, DeliveryAddress> {

    override fun mapFromEntity(model: AddressEntity): DeliveryAddress = DeliveryAddress(
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

    override fun mapToEntity(model: DeliveryAddress): AddressEntity = AddressEntity(
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
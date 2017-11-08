package si.lanisnik.restaurantorder.remote.address.mapper

import si.lanisnik.restaurantorder.data.entity.address.AddressEntity
import si.lanisnik.restaurantorder.remote.address.model.AddressDto
import si.lanisnik.restaurantorder.remote.base.mapper.RemoteEntityMapper
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
class AddressRemoteMapper @Inject constructor() : RemoteEntityMapper<AddressDto, AddressEntity> {

    override fun mapFromRemote(remoteModel: AddressDto): AddressEntity = AddressEntity(
            remoteModel.id,
            remoteModel.street,
            remoteModel.number,
            remoteModel.city,
            remoteModel.postcode,
            remoteModel.note,
            remoteModel.selected,
            remoteModel.deleted,
            remoteModel.customerId
    )

}
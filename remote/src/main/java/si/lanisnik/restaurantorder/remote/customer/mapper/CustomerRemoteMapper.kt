package si.lanisnik.restaurantorder.remote.customer.mapper

import si.lanisnik.restaurantorder.data.entity.customer.CustomerEntity
import si.lanisnik.restaurantorder.remote.base.mapper.RemoteEntityMapper
import si.lanisnik.restaurantorder.remote.customer.model.CustomerDto
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 02/11/2017.
 * domen.lanisnik@gmail.com
 */
class CustomerRemoteMapper @Inject constructor() : RemoteEntityMapper<CustomerDto, CustomerEntity> {

    override fun mapFromRemote(remoteModel: CustomerDto): CustomerEntity = CustomerEntity(
            remoteModel.id,
            remoteModel.email,
            remoteModel.password,
            remoteModel.firstName,
            remoteModel.lastName,
            remoteModel.phoneNumber,
            remoteModel.address
    )

}
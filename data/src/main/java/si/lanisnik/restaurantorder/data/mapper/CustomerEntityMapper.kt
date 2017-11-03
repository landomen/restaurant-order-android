package si.lanisnik.restaurantorder.data.mapper

import si.lanisnik.restaurantorder.data.entity.customer.CustomerEntity
import si.lanisnik.restaurantorder.domain.model.customer.Customer
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 03/11/2017.
 * domen.lanisnik@gmail.com
 */
class CustomerEntityMapper @Inject constructor() : EntityMapper<CustomerEntity, Customer> {
    override fun mapFromEntity(model: CustomerEntity): Customer {
        return Customer(
                model.id,
                model.email,
                model.password,
                model.firstName,
                model.lastName,
                model.phoneNumber,
                model.address
        )
    }

    override fun mapToEntity(model: Customer): CustomerEntity {
        return CustomerEntity(
                model.id,
                model.email,
                model.password,
                model.firstName,
                model.lastName,
                model.phoneNumber,
                model.address
        )
    }
}
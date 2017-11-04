package si.lanisnik.restaurantorder.mapper

import si.lanisnik.restaurantorder.domain.model.customer.Customer
import si.lanisnik.restaurantorder.ui.customer.model.CustomerModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
class CustomerModelMapper @Inject constructor() : PresentationMapper<Customer, CustomerModel> {

    override fun mapToModel(model: Customer): CustomerModel {
        return CustomerModel(
                model.id,
                model.email,
                model.password,
                model.firstName,
                model.lastName,
                model.phoneNumber,
                model.address
        )
    }

    override fun mapFromModel(model: CustomerModel): Customer {
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

}
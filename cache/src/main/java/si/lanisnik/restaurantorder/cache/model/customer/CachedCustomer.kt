package si.lanisnik.restaurantorder.cache.model.customer

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Domen Lanišnik on 03/11/2017.
 * domen.lanisnik@gmail.com
 */
open class CachedCustomer() : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var email: String = ""
    var password: String? = ""
    var firstName: String = ""
    var lastName: String = ""
    var phoneNumber: String = ""
    var address: String = ""

    constructor(id: Int, email: String, password: String?, firstName: String, lastName: String, phoneNumber: String, address: String) : this() {
        this.id = id
        this.email = email
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNumber = phoneNumber
        this.address = address
    }
}
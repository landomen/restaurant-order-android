package si.lanisnik.restaurantorder.cache.model.order.shoppingcart

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Domen Lanišnik on 11/11/2017.
 * domen.lanisnik@gmail.com
 */
open class SelectedMenuItemDb() : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var count: Int = 0
    var comment: String? = null

    constructor(id: Int, count: Int, comment: String?) : this() {
        this.id = id
        this.count = count
        this.comment = comment
    }
}
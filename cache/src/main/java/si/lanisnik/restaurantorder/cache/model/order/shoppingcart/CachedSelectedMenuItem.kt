package si.lanisnik.restaurantorder.cache.model.order.shoppingcart

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import si.lanisnik.restaurantorder.cache.model.menuitem.CachedMenuItem

/**
 * Created by Domen Lanišnik on 11/11/2017.
 * domen.lanisnik@gmail.com
 */
open class CachedSelectedMenuItem() : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var menuItem: CachedMenuItem? = null
    var comment: String? = null

    constructor(id: Long, menuItem: CachedMenuItem, comment: String?) : this() {
        this.id = id
        this.menuItem = menuItem
        this.comment = comment
    }
}
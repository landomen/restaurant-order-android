package si.lanisnik.restaurantorder.cache.model.order.shoppingcart

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by Domen Lanišnik on 11/11/2017.
 * domen.lanisnik@gmail.com
 */
open class CachedShoppingCart() : RealmObject() {
    var selectedMenuItems = RealmList<CachedSelectedMenuItem>()

    constructor(selectedMenuItems: RealmList<CachedSelectedMenuItem>) : this() {
        this.selectedMenuItems = selectedMenuItems
    }
}
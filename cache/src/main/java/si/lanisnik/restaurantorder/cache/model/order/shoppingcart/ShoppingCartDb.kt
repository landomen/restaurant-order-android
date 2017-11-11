package si.lanisnik.restaurantorder.cache.model.order.shoppingcart

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by Domen Lanišnik on 11/11/2017.
 * domen.lanisnik@gmail.com
 */
open class ShoppingCartDb() : RealmObject() {
    var selectedMenuItems = RealmList<SelectedMenuItemDb>()

    constructor(selectedMenuItems: RealmList<SelectedMenuItemDb>) : this() {
        this.selectedMenuItems = selectedMenuItems
    }
}
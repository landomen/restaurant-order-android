package si.lanisnik.restaurantorder.cache.model.foodcategory

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
open class CachedFoodCategory() : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var title: String = ""

    constructor(id: Int, title: String) : this() {
        this.id = id
        this.title = title
    }
}
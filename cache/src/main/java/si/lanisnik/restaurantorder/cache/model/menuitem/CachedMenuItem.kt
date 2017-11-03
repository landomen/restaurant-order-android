package si.lanisnik.restaurantorder.cache.model.menuitem

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import si.lanisnik.restaurantorder.cache.model.foodcategory.CachedFoodCategory

/**
 * Created by Domen Lanišnik on 24/09/2017.
 * domen.lanisnik@gmail.com
 */
open class CachedMenuItem() : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var image: String? = null
    var price: Double = 0.0
    var category: CachedFoodCategory? = null

    constructor(id: Int, title: String, description: String, image: String?, price: Double, category: CachedFoodCategory?) : this() {
        this.id = id
        this.title = title
        this.description = description
        this.image = image
        this.price = price
        this.category = category
    }
}
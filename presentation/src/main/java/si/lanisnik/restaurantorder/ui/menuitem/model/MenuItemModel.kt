package si.lanisnik.restaurantorder.ui.menuitem.model

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import si.lanisnik.restaurantorder.ui.foodcategory.model.FoodCategoryModel

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
@Parcel(Parcel.Serialization.BEAN)
class MenuItemModel @ParcelConstructor constructor(val id: Int = 0,
                                                   val title: String,
                                                   val description: String,
                                                   val image: String? = null,
                                                   val price: Double,
                                                   val category: FoodCategoryModel) {

    fun getFormattedPrice(): String = "$price€"

}
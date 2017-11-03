package si.lanisnik.restaurantorder.ui.foodcategory.model

import org.parceler.Parcel
import org.parceler.ParcelConstructor

/**
 * Created by Domen Lanišnik on 28/09/2017.
 * domen.lanisnik@gmail.com
 */
@Parcel(Parcel.Serialization.BEAN)
data class FoodCategoryModel @ParcelConstructor constructor(val id: Int, val title: String)
package si.lanisnik.restaurantorder.menuitem.model

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
data class MenuItemModel(val id: Int = 0,
                         val title: String,
                         val description: String,
                         val image: String? = null,
                         val price: Double,
                         val categoryId: Int)
package si.lanisnik.restaurantorder.data.entity.menuitems

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class MenuItem(
        val id: Int = 0,
        val title: String,
        val description: String,
        val image: String? = null,
        val price: Double,
        val category: FoodCategory
)
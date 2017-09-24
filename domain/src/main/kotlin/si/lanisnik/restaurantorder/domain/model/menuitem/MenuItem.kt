package si.lanisnik.restaurantorder.domain.model.menuitem

/**
 * Created by Domen Lanišnik on 21/09/2017.
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
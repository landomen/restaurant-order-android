package si.lanisnik.restaurantorder.data.entity.menuitems

/**
 * Created by Domen Lanišnik on 03/09/2017.
 * domen.lanisnik@gmail.com
 */
data class AllMenuItemsResponse(val categories: List<GroupedByCategory>)

data class GroupedByCategory(
        val title: String,
        val menuItems: MutableList<MenuItem>
)

data class MenuItem(
        val id: Int = 0,
        val title: String,
        val description: String,
        val image: String? = null,
        val price: Double,
        val category: FoodCategory
)

data class FoodCategory(
        val id: Int = 0,
        val title: String = ""
)
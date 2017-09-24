package si.lanisnik.restaurantorder.data.net.model.menuitem

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class GroupedByCategory(
        val category: FoodCategoryDto,
        val menuItems: List<MenuItemDto>
)
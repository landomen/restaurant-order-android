package si.lanisnik.restaurantorder.remote.model.menuitem

import si.lanisnik.restaurantorder.remote.model.foodcategory.FoodCategoryDto

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class MenuItemDto(
        val id: Int = 0,
        val title: String,
        val description: String,
        val image: String? = null,
        val price: Double,
        val category: FoodCategoryDto
)
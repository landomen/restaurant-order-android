package si.lanisnik.restaurantorder.data.entity.menuitem

/**
 * Presentation of the MenuItem on the data layer.
 */
data class MenuItemEntity(
        val id: Int = 0,
        val title: String,
        val description: String,
        val image: String? = null,
        val price: Double,
        val category: Int
)
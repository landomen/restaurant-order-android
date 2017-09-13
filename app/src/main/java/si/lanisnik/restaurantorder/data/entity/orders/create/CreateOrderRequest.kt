package si.lanisnik.restaurantorder.data.entity.orders.create

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class CreateOrderRequest (
        val address: String = "",
        val comment: String? = null,
        val menuItems: List<SelectedMenuItem> = listOf()
)
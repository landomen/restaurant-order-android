package si.lanisnik.restaurantorder.remote.order.model.create

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class CreateOrderRequest(
        val addressId: Int,
        val comment: String?,
        val items: List<SelectedMenuItemDto>
)
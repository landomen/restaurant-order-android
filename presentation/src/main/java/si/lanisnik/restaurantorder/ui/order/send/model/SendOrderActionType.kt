package si.lanisnik.restaurantorder.ui.order.send.model

/**
 * Created by Domen Lanišnik on 18/11/2017.
 * domen.lanisnik@gmail.com
 */
sealed class SendOrderActionType {
    class GetAddresses : SendOrderActionType()
    data class CreateOrder(val selectedAddressId: Int, val comment: String) : SendOrderActionType()
}
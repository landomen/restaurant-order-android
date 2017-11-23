package si.lanisnik.restaurantorder.ui.order.model

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderStatusModel(val status: OrderStatusType,
                            val changeTime: Long,
                            val reason: String?,
                            val estimatedDeliveryTime: Long?)
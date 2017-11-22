package si.lanisnik.restaurantorder.domain.model.order

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderStatus(val status: OrderStatusEnum,
                       val changeTime: Long,
                       val reason: String?,
                       val estimatedDeliveryTime: Long?)
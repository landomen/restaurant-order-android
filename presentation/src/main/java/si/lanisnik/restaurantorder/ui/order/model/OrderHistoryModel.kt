package si.lanisnik.restaurantorder.ui.order.model

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderHistoryModel(val id: Int,
                             val total: Double,
                             val createdAt: Long,
                             val itemsCount: Int,
                             val status: OrderStatusType)
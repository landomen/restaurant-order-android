package si.lanisnik.restaurantorder.domain.model.order

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderHistory(val id: Int,
                        val total: Double,
                        val createdAt: Long,
                        val itemsCount: Int,
                        val status: OrderStatusEnum)
package si.lanisnik.restaurantorder.ui.order.model

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import si.lanisnik.restaurantorder.domain.model.order.OrderStatusEnum

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
@Parcel(Parcel.Serialization.BEAN)
data class OrderStatusModel @ParcelConstructor constructor(val status: OrderStatusEnum,
                                                           val changeTime: Long,
                                                           val reason: String?,
                                                           val estimatedDeliveryTime: Long?)
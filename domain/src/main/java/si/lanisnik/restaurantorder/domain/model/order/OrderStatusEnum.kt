package si.lanisnik.restaurantorder.domain.model.order

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
enum class OrderStatusEnum {
    CREATED,
    CONFIRMED,
    REJECTED,
    IN_PROGRESS,
    READY,
    FINISHED,
    CANCELLED
}
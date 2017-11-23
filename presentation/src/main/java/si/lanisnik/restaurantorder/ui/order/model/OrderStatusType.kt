package si.lanisnik.restaurantorder.ui.order.model

import android.support.annotation.StringRes
import si.lanisnik.restaurantorder.R

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
enum class OrderStatusType(@StringRes val resId: Int) {
    CREATED(R.string.order_status_created),
    CONFIRMED(R.string.order_status_confirmed),
    REJECTED(R.string.order_status_rejected),
    IN_PROGRESS(R.string.order_status_in_progress),
    READY(R.string.order_status_ready),
    FINISHED(R.string.order_status_finished),
    CANCELLED(R.string.order_status_cancelled),
}
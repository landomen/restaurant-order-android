package si.lanisnik.restaurantorder.ui.order.navigator

import android.content.Context
import org.jetbrains.anko.startActivity
import si.lanisnik.restaurantorder.ui.order.shoppingcart.OrderPreviewActivity
import si.lanisnik.restaurantorder.ui.order.send.SendOrderActivity
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 17/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderNavigator @Inject constructor() {

    fun navigateToShoppingCart(context: Context) {
        context.startActivity<OrderPreviewActivity>()
    }

    fun navigateToSendOrder(context: Context) {
        context.startActivity<SendOrderActivity>()
    }
}
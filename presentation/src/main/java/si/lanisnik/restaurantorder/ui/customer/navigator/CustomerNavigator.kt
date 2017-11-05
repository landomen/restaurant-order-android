package si.lanisnik.restaurantorder.ui.customer.navigator

import android.content.Context
import org.jetbrains.anko.startActivity
import si.lanisnik.restaurantorder.ui.customer.password.ChangePasswordActivity
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class CustomerNavigator @Inject constructor() {

    fun navigateToChangePassword(context: Context) {
        context.startActivity<ChangePasswordActivity>()
    }
}
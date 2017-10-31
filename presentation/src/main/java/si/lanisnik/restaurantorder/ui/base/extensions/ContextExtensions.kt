package si.lanisnik.restaurantorder.ui.base.extensions

import android.content.Context
import android.support.annotation.StringRes
import si.lanisnik.restaurantorder.ui.base.dialogs.DialogHelper
import si.lanisnik.restaurantorder.ui.base.other.SimpleListener

/**
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
fun Context.showErrorDialogWithRetryAndDismissCallback(@StringRes content: Int,
                                                       onRetry: SimpleListener,
                                                       onDismiss: SimpleListener) =
        DialogHelper.showErrorDialogWithRetryAndDismissCallback(this, content, onRetry, onDismiss)
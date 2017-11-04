package si.lanisnik.restaurantorder.ui.base.dialogs

import android.content.Context
import android.support.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.other.SimpleListener

/**
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
object DialogHelper {

    fun showSuccessDialog(context: Context, @StringRes content: Int, listener: SimpleListener?) {
        val builder = createBaseDialog(context, listener == null, content)
                .positiveText(R.string.general_ok)
        listener?.let {
            builder.onPositive(wrapCallback(it))
        }
        builder.show()
    }

    private fun createBaseDialog(context: Context,
                                 cancelable: Boolean,
                                 @StringRes content: Int): MaterialDialog.Builder {
        return MaterialDialog.Builder(context)
                .cancelable(cancelable)
                .canceledOnTouchOutside(cancelable)
                .content(content)
    }

    private fun wrapCallback(callback: SimpleListener): MaterialDialog.SingleButtonCallback {
        return MaterialDialog.SingleButtonCallback { dialog, _ ->
            dialog.dismiss()
            callback.invoke()
        }
    }

}
package si.lanisnik.restaurantorder.presentation.base.dialogs

import android.content.Context
import android.support.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.presentation.base.other.SimpleListener

/**
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
object DialogHelper {

    // region Public methods

    fun showErrorDialogWithRetryAndDismissCallback(context: Context, @StringRes content: Int,
                                                   onRetry: SimpleListener,
                                                   onDismiss: SimpleListener) {
        if (content > 0) {
            createBaseErrorDialog(context, content)
                    .onNegative(wrapCallback(onDismiss))
                    .onPositive(wrapCallback(onRetry))
                    .positiveText(R.string.general_retry)
                    .show()
        }
    }

    // endregion

    // region Private methods

    private fun createBaseErrorDialog(context: Context, @StringRes content: Int): MaterialDialog.Builder =
            createBaseDialog(context, false)
                    .content(content)
                    .negativeText(R.string.general_close)


    private fun createBaseDialog(context: Context, cancelable: Boolean): MaterialDialog.Builder =
            MaterialDialog.Builder(context)
                    .cancelable(cancelable)
                    .canceledOnTouchOutside(cancelable)
                    .title(R.string.general_dialog_notification_title)
                    .titleColorRes(R.color.primary_text)
                    .contentColorRes(R.color.secondary_text)
                    .positiveColorRes(R.color.accent)
                    .negativeColorRes(R.color.accent)
                    .backgroundColorRes(android.R.color.white)

    private fun wrapCallback(callback: SimpleListener): MaterialDialog.SingleButtonCallback =
            MaterialDialog.SingleButtonCallback { dialog, _ ->
                dialog.dismiss()
                callback.invoke()
            }

    // endregion
}
package si.lanisnik.restaurantorder.ui.address.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_add_address.view.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.extensions.input

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
class AddAddressDialogFragment : DialogFragment() {

    interface AddAddressDialogListener {
        fun onAddressCreated(address: String, note: String, default: Boolean)
    }

    companion object {
        private const val ARG_ADDRESS = "selectedAddress"

        fun show(activity: Activity, selectedAddress: String) {
            AddAddressDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ADDRESS, selectedAddress)
                }
            }.show(activity.fragmentManager, AddAddressDialogFragment::class.java.simpleName)
        }
    }

    private var listener: AddAddressDialogListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddAddressDialogListener)
            listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val address = arguments.getString(ARG_ADDRESS, "")
        val view = activity.layoutInflater.inflate(R.layout.dialog_add_address, null)
        view.addAddressEditText.setText(address)
        return AlertDialog.Builder(activity)
                .setTitle(R.string.address_add_title)
                .setMessage(R.string.address_add_message)
                .setView(view)
                .setPositiveButton(R.string.general_save, { _: DialogInterface, _: Int ->
                    listener?.onAddressCreated(address, view.addAddressNoteEditText.input(), view.addAddressDefaultCheckBox.isChecked)
                })
                .setNegativeButton(R.string.general_cancel, null)
                .create()
    }

}
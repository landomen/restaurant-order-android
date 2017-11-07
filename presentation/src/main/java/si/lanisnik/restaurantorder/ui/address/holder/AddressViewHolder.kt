package si.lanisnik.restaurantorder.ui.address.holder

import android.view.View
import kotlinx.android.synthetic.main.recycler_item_address.view.*
import si.lanisnik.restaurantorder.ui.address.model.AddressModel
import si.lanisnik.restaurantorder.ui.base.extensions.changeVisibility
import si.lanisnik.restaurantorder.ui.base.holder.BaseViewHolder

/**
 * Created by Domen Lanišnik on 07/11/2017.
 * domen.lanisnik@gmail.com
 */
class AddressViewHolder(itemView: View) : BaseViewHolder<AddressModel>(itemView) {

    private val radioButton by lazy { itemView.addressRadioButton }
    private val titleText by lazy { itemView.addressTitleText }
    private val noteText by lazy { itemView.addressNoteText }
    private val editButton by lazy { itemView.addressEditImage }

    override fun bindModel(model: AddressModel) {
        titleText.text = model.getAddress()

        noteText.text = model.note
        noteText.changeVisibility(model.note != null)

        radioButton.isChecked = model.selected
    }

    fun listenForEdit(listener: (View) -> Unit) {
        editButton.setOnClickListener(listener)
    }

    fun listerForSelection(listener: () -> Unit) {
        radioButton.setOnCheckedChangeListener { _, checked ->
            if (checked) listener()
        }
    }

}
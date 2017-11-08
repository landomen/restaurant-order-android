package si.lanisnik.restaurantorder.ui.address.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.address.holder.AddressViewHolder
import si.lanisnik.restaurantorder.ui.address.model.AddressModel
import si.lanisnik.restaurantorder.ui.base.extensions.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by Domen Lanišnik on 07/11/2017.
 * domen.lanisnik@gmail.com
 */
class AddressRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<AddressViewHolder>() {

    var listener: AddressListener? = null
    var addresses: MutableList<AddressModel> by Delegates.observable(mutableListOf()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    old[oldItemPosition].id == new[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldAddress = old[oldItemPosition]
                val newAddress = new[newItemPosition]
                return oldAddress.id == newAddress.id && oldAddress.selected == newAddress.selected
            }

        }).dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = addresses[position]
        holder.bindModel(address)
        holder.listenForEdit {
            listener?.onAddressDelete(address.id)
        }
        holder.listerForSelection {
            listener?.onAddressSelected(address)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder =
            AddressViewHolder(parent.inflate(R.layout.recycler_item_address, false))

    override fun getItemCount(): Int = addresses.size

    interface AddressListener {
        fun onAddressSelected(address: AddressModel)
        fun onAddressDelete(addressId: Int)
    }
}
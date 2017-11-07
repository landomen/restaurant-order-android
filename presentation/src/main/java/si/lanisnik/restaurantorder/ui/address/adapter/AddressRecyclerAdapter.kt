package si.lanisnik.restaurantorder.ui.address.adapter

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

    var addresses: MutableList<AddressModel> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }
    var listener: AddressListener? = null

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = addresses[position]
        holder.bindModel(address)
        holder.listenForEdit {
            listener?.onAddressEdit(address)
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
        fun onAddressEdit(address: AddressModel)
    }
}
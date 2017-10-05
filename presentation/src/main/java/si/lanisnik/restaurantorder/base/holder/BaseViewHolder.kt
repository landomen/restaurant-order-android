package si.lanisnik.restaurantorder.base.holder

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
abstract class BaseViewHolder<in M>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindModel(model: M)
}
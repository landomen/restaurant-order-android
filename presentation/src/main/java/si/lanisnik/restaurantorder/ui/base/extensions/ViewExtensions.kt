package si.lanisnik.restaurantorder.ui.base.extensions

import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import org.jetbrains.anko.layoutInflater

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.changeVisibility(show: Boolean) {
    if (show)
        show()
    else
        hide()
}

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false): View =
        context.layoutInflater.inflate(layout, this, attachToRoot)

fun RecyclerView.enableItemDividers() {
    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
}

fun EditText.input(): String = text.trim().toString()

fun View.snackbar(@StringRes message: Int) =
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
package si.lanisnik.restaurantorder.ui.base.extensions

import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import org.jetbrains.anko.layoutInflater

// region General

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

fun View.snackbar(@StringRes message: Int) =
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false): View =
        context.layoutInflater.inflate(layout, this, attachToRoot)

// endregion

// region Edit text

fun EditText.input(): String = text.trim().toString()

fun EditText.onDoneAction(listener: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE)
            listener.invoke()
        false
    }
}

// endregion

fun RecyclerView.enableItemDividers() {
    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
}


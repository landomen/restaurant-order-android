package si.lanisnik.restaurantorder.ui.base.extensions

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import org.parceler.Parcels

fun <T> Activity.unwrapParcel(extraName: String): T = Parcels.unwrap(intent.getParcelableExtra(extraName))

fun AppCompatActivity.enableBackArrow() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

fun Activity.hideKeyboard() {
    currentFocus?.windowToken?.let {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(it, 0)
    }
}

inline fun <reified T : ViewModel> AppCompatActivity.createViewModel(factory: ViewModelProvider.Factory): T =
        ViewModelProviders.of(this, factory)[T::class.java]
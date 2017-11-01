package si.lanisnik.restaurantorder.ui.base.extensions

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import org.parceler.Parcels

fun <T> Activity.unwrapParcel(extraName: String): T = Parcels.unwrap(intent.getParcelableExtra(extraName))

fun AppCompatActivity.enableBackArrow() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}
package si.lanisnik.restaurantorder.ui.base.extensions

import android.app.Activity
import org.parceler.Parcels

fun <T> Activity.unwrapParcel(extraName: String): T = Parcels.unwrap(intent.getParcelableExtra(extraName))
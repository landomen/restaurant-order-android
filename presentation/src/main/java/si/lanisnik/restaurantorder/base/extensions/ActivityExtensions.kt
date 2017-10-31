package si.lanisnik.restaurantorder.base.extensions

import android.app.Activity
import org.parceler.Parcels

fun <T> Activity.unwrapParcel(extraName: String): T = Parcels.unwrap(intent.getParcelableExtra(extraName))
package si.lanisnik.restaurantorder.ui.base.extensions

import android.util.Patterns

fun <T> Collection<T>?.isNullOrEmpty(): Boolean = this == null || this.isEmpty()
fun <T> Collection<T>?.isNotNullAndEmpty(): Boolean = !this.isNullOrEmpty()

fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun String.isValidPassword(): Boolean = length > 3
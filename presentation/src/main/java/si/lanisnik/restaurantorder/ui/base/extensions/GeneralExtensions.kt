package si.lanisnik.restaurantorder.ui.base.extensions

fun <T> Collection<T>?.isNullOrEmpty(): Boolean = this == null || this.isEmpty()
fun <T> Collection<T>?.isNotNullAndEmpty(): Boolean = !this.isNullOrEmpty()
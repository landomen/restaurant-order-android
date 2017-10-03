package si.lanisnik.restaurantorder.cache.model.validity

/**
 * Created by Domen Lanišnik on 02/10/2017.
 * domen.lanisnik@gmail.com
 */
enum class CachedDataType(val id: Int) {
    FOOD_CATEGORY(0),
    MENU_ITEMS(1),
    ORDERS_(2);

    companion object {
        fun fromId(id: Int): CachedDataType = values().find { it.id == id }!!
    }
}
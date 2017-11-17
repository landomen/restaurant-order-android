package si.lanisnik.restaurantorder.domain.model.order

import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem

/**
 * Created by Domen Lanišnik on 10/11/2017.
 * domen.lanisnik@gmail.com
 */
data class SelectedMenuItem(var id: Long, val menuItem: MenuItem, var comment: String?)
package si.lanisnik.restaurantorder.menuitem.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.intentFor
import si.lanisnik.restaurantorder.R

class MenuItemsListActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_CATEGORY_ID = "si.lanisnik.restaurantorder.menuitem.list.CategoryId"

        /**
         * Create intent to open [MenuItemsListActivity] based on the selected category.
         */
        fun create(context: Context, categoryId: Int): Intent =
                context.intentFor<MenuItemsListActivity>(EXTRA_CATEGORY_ID to categoryId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_items_list)
    }
}

package si.lanisnik.restaurantorder.menuitem.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_menu_items_list.*
import org.jetbrains.anko.intentFor
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.base.extensions.enableItemDividers
import si.lanisnik.restaurantorder.base.extensions.hide
import si.lanisnik.restaurantorder.menuitem.list.adapter.MenuitemRecyclerAdapter
import si.lanisnik.restaurantorder.menuitem.model.MenuItemModel
import javax.inject.Inject

class MenuItemsListActivity : AppCompatActivity(), MenuItemsListContract.View {

    companion object {
        private const val EXTRA_CATEGORY_ID = "si.lanisnik.restaurantorder.menuitem.list.CategoryId"

        /**
         * Create intent to open [MenuItemsListActivity] based on the selected category.
         */
        fun create(context: Context, categoryId: Int): Intent =
                context.intentFor<MenuItemsListActivity>(EXTRA_CATEGORY_ID to categoryId)
    }

    @Inject lateinit var adapter: MenuitemRecyclerAdapter
    @Inject lateinit var presenter: MenuItemsListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_items_list)
        AndroidInjection.inject(this)

        initRecyclerView()
        initPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }

    override fun showMenuItems(menuItems: List<MenuItemModel>) {
        adapter.items = menuItems
        menuItemLoadingView.hide()
    }

    private fun initRecyclerView() {
        menuItemsRecyclerView.adapter = adapter
        menuItemsRecyclerView.enableItemDividers()
    }

    private fun initPresenter() {
        presenter.initialize(intent.extras.getInt(EXTRA_CATEGORY_ID))
    }
}

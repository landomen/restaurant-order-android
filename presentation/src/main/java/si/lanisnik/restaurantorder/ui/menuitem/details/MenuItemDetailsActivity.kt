package si.lanisnik.restaurantorder.ui.menuitem.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_menu_item_details.*
import kotlinx.android.synthetic.main.content_menu_item_details.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.constants.ActivityConstants.EXTRA_MENU_ITEM
import si.lanisnik.restaurantorder.ui.base.extensions.unwrapParcel
import javax.inject.Inject

class MenuItemDetailsActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: MenuItemDetailsViewModelFactory
    private lateinit var viewModel: MenuItemDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[MenuItemDetailsViewModel::class.java]
        initViewModel()
    }

    override fun getContentView(): Int = R.layout.activity_menu_item_details

    override fun initToolbar() {
        setSupportActionBar(menuItemToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        menuItemToolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {

    }

    private fun initViewModel() {
        viewModel.initialize(unwrapParcel(EXTRA_MENU_ITEM))
        title = viewModel.getTitle()

        menuItemDescriptionTextView.text = viewModel.getMenuItemDescription()
        menuItemPriceTextView.text = viewModel.getMenuItemPrice()
        displayImage()
    }

    private fun displayImage() {
        viewModel.getMenuItemImageUrl()?.let {
            Glide.with(this)
                    .load(it)
                    .into(menuItemImageView)
        }
    }
}

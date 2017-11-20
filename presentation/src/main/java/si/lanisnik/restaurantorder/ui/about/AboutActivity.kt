package si.lanisnik.restaurantorder.ui.about

import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.extensions.enableBackArrow

/**
 * Created by Domen Lanišnik on 20/11/2017.
 * domen.lanisnik@gmail.com
 */
class AboutActivity : BaseActivity() {

    override fun getContentView(): Int = R.layout.activity_about

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.about_title)
        enableBackArrow()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {

    }

    override fun initViewModel() {

    }

    override fun setupObservers() {

    }
}
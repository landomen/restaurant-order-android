package si.lanisnik.restaurantorder.presentation.dashboard

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.presentation.base.BaseActivity
import si.lanisnik.restaurantorder.presentation.categories.CategoriesListActivity

class DashboardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)
        setTitle(R.string.app_name)

        initUi()
    }

    private fun initUi() {
        floatingActionButton.setOnClickListener {
            startActivity<CategoriesListActivity>()
        }
    }

}

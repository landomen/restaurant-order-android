package si.lanisnik.restaurantorder.dashboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.jetbrains.anko.startActivity
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.foodcategory.CategoriesListActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(dashboardToolbar)
        setTitle(R.string.app_name)

        initUi()
    }

    private fun initUi() {
        floatingActionButton.setOnClickListener {
            startActivity<CategoriesListActivity>()
        }
    }

}

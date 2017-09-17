package si.lanisnik.restaurantorder.presentation.splash

import android.os.Bundle
import org.jetbrains.anko.startActivity
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.presentation.base.BaseActivity
import si.lanisnik.restaurantorder.presentation.dashboard.DashboardActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity<DashboardActivity>()
    }
}

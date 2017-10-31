package si.lanisnik.restaurantorder.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.dashboard.DashboardActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity<DashboardActivity>()
    }
}

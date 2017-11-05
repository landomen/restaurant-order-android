package si.lanisnik.restaurantorder.ui.customer.profile

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.extensions.createViewModel
import si.lanisnik.restaurantorder.ui.base.extensions.enableBackArrow
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class ProfileActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ProfileViewModelFactory
    private lateinit var viewModel: ProfileViewModel

    override fun getContentView(): Int = R.layout.activity_profile

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        enableBackArrow()
        setTitle(R.string.dashboard_profile)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {

    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
    }

    override fun setupObservers() {
        viewModel.getDetailsObservable().observe(this, Observer {

        })
    }

}
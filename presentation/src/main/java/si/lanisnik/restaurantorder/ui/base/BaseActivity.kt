package si.lanisnik.restaurantorder.ui.base

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import org.jetbrains.anko.indeterminateProgressDialog
import si.lanisnik.restaurantorder.R
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
abstract class BaseActivity : AppCompatActivity(), HasActivityInjector {

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    private var loadingDialog: Dialog? = null

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        AndroidInjection.inject(this)
        initToolbar()
        initUi()
        initViewModel()
        setupObservers()
    }

    override fun onDestroy() {
        hideLoadingDialog()
        super.onDestroy()
    }

    protected fun showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = indeterminateProgressDialog(R.string.general_loading) {
                setCancelable(false)
                setCanceledOnTouchOutside(false)
            }
        }
        loadingDialog?.show()
    }

    protected fun hideLoadingDialog() = loadingDialog?.dismiss()

    @LayoutRes abstract protected fun getContentView(): Int
    abstract protected fun initToolbar()
    abstract protected fun initUi()
    abstract protected fun initViewModel()
    abstract protected fun setupObservers()
}
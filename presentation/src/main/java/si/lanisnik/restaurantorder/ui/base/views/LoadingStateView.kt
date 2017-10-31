package si.lanisnik.restaurantorder.ui.base.views

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_loading_state.view.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.extensions.hide
import si.lanisnik.restaurantorder.ui.base.extensions.inflate
import si.lanisnik.restaurantorder.ui.base.extensions.show
import kotlin.properties.Delegates

/**
 * Simple view that shows different states of data loading process.
 *
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
class LoadingStateView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    enum class State(val text: Int) {
        LOADING(R.string.result_loading),
        EMPTY(R.string.result_loading_empty),
        ERROR(R.string.result_loading_error)
    }

    var retryListener: RetryListener? = null
    var state: State by Delegates.observable(State.LOADING) { _, _, new ->
        onStateChange(new)
    }

    init {
        inflate(R.layout.view_loading_state, true)
        retryButton.setOnClickListener { retryListener?.onRetryClicked() }
    }

    private fun onStateChange(state: State) {
        loadingStateText.setText(state.text)
        when (state) {
            LoadingStateView.State.LOADING -> setupLoadingState()
            LoadingStateView.State.EMPTY, LoadingStateView.State.ERROR -> setupRetryState()
        }
    }

    private fun setupLoadingState() {
        progressBar.show()
        retryButton.hide()
    }

    private fun setupRetryState() {
        progressBar.hide()
        retryButton.show()
    }

    interface RetryListener {
        fun onRetryClicked()
    }
}
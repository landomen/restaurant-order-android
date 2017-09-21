package si.lanisnik.restaurantorder.base.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.base.extensions.inflate

/**
 * Simple view that shows a progress bar and a please wait text.
 *
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
class LoadingView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        inflate(R.layout.view_loading, true)
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }
}
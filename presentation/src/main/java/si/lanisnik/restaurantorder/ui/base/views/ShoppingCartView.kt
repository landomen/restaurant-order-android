package si.lanisnik.restaurantorder.ui.base.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.view_shopping_cart.view.*
import org.jetbrains.anko.dimen
import org.jetbrains.anko.padding
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.extensions.changeVisibility
import si.lanisnik.restaurantorder.ui.base.extensions.inflate
import si.lanisnik.restaurantorder.ui.base.views.ShoppingCartView.IconSize.NORMAL
import si.lanisnik.restaurantorder.ui.base.views.ShoppingCartView.IconSize.SMALL
import kotlin.properties.Delegates

/**
 * Created by Domen Lanišnik on 11/11/2017.
 * domen.lanisnik@gmail.com
 */
class ShoppingCartView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    enum class IconSize {
        NORMAL, SMALL
    }

    private val iconSizeMap = mapOf(
            NORMAL to dimen(R.dimen.shopping_cart_icon_normal),
            SMALL to dimen(R.dimen.shopping_cart_icon_small)
    )
    private var iconSize = NORMAL

    var animateChange: Boolean = false
    var count: Int by Delegates.observable(0) { _, old, new ->
        if (new > old && animateChange) {
            animateNewItem()
        }
        updateIcon(if (new > 0) SMALL else NORMAL)
        updateText(new)
    }

    init {
        inflate(R.layout.view_shopping_cart, true)
        clipChildren = false
        clipToPadding = false
        padding = dimen(R.dimen.space_small)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        shoppingCartFab.setOnClickListener(l)
    }

    private fun animateNewItem() {
        YoYo.with(Techniques.Pulse)
                .duration(500)
                .playOn(this)
    }

    private fun updateIcon(size: IconSize) {
        if (size != iconSize) {
            shoppingCartIcon.layoutParams.apply {
                width = iconSizeMap[size]!!
                height = iconSizeMap[size]!!
            }
            iconSize = size
        }
    }

    private fun updateText(count: Int) {
        shoppingCartCountText.text = count.toString()
        shoppingCartCountText.changeVisibility(count > 0)
    }
}
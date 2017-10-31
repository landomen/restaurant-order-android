package si.lanisnik.restaurantorder.base.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_horizontal_number_picker.view.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.base.extensions.inflate
import kotlin.properties.Delegates

/**
 * Created by Domen Lanišnik on 31/10/2017.
 * domen.lanisnik@gmail.com
 */
class HorizontalNumberPicker(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    companion object {
        private const val VALUE_MIN = 1
        private const val VALUE_MAX = 10
    }

    var value: Int by Delegates.observable(VALUE_MIN) { _, _, new ->
        updateValueText(new)
    }

    init {
        inflate(R.layout.view_horizontal_number_picker, true)
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        setupListeners()
    }

    private fun setupListeners() {
        pickerLowerButton.setOnClickListener {
            if (value > VALUE_MIN)
                value--
        }
        pickerHigherButton.setOnClickListener {
            if (value < VALUE_MAX)
                value++
        }
    }

    private fun updateValueText(value: Int) {
        pickerValueText.text = value.toString()
    }
}
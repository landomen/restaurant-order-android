package si.lanisnik.restaurantorder.ui.onboarding.model

import android.support.annotation.StringRes
import si.lanisnik.restaurantorder.R

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
enum class InputError(@StringRes val message: Int) {
    MISSING(R.string.error_missing_fields),
    REQUIRED(R.string.error_required_field),
    EMAIL(R.string.error_invalid_email),
    PHONE_NUMBER(R.string.error_invalid_phone_number),
    PASSWORD_CONFIRMATION(R.string.error_password_confirmation)
}
package si.lanisnik.restaurantorder.ui.base.utilities

import android.content.Context
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import si.lanisnik.restaurantorder.internal.injection.scopes.PerApplication
import javax.inject.Inject

/**
 * Small util class for validating local phone numbers.
 *
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
@PerApplication
class PhoneNumberValidator @Inject constructor(context: Context) {

    private val phoneNumberUtil = PhoneNumberUtil.createInstance(context)

    fun isValid(phoneNumber: String): Boolean = try {
        with(phoneNumberUtil) {
            isValidNumber(parse(phoneNumber, "SI"))
        }
    } catch (e: Exception) {
        false
    }
}
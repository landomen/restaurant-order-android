package si.lanisnik.restaurantorder.cache.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import si.lanisnik.restaurantorder.cache.preferences.keys.LongKey
import si.lanisnik.restaurantorder.cache.preferences.keys.StorageKey
import si.lanisnik.restaurantorder.cache.preferences.keys.StringKey
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Wrapper around [SharedPreferences] that supports insertion/retrieval of only pre-defined keys.
 */
@Singleton
class SimpleStorage @Inject constructor(context: Context) {
    companion object {
        private const val SHARED_PREFERENCES_NAME = "PartnerSimpleStorage"
    }

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Saves the value for this key.
     *
     * @param key Key to save
     * @param value Value to save
     * @param suffix Additional text to add to key name (useful for dynamic keys)
     *
     * @return Instance of SimpleStorage for further use
     */
    fun putString(key: StringKey, value: String? = null, suffix: String = ""): SimpleStorage {
        sharedPreferences.edit().putString(key.name + suffix, value).apply()
        return this
    }

    /**
     * Reads the key from storage.
     *
     * @param key Key to retrieve
     * @param defaultValue Value to use if key doesn't exist
     * @param suffix Additional text to add to key name (useful for dynamic keys)
     */
    fun getString(key: StringKey, defaultValue: String? = null, suffix: String = ""): String? =
            sharedPreferences.getString(key.name + suffix, defaultValue)

    /**
     * Saves the Long value for this key.
     *
     * @param key Key under which to save value
     * @param value Value to save
     * @param suffix Additional text to add to key name (useful for dynamic keys)
     */
    fun putLong(key: LongKey, value: Long, suffix: String = ""): SimpleStorage {
        sharedPreferences.edit().putLong(key.name + suffix, value).apply()
        return this
    }

    /**
     * Reads the value under the key.
     *
     * @param key Key to retrieve
     * @param defaultValue Value to use if key doesn't exist
     * @param suffix Additional text to add to key name (useful for dynamic keys)
     */
    fun getLong(key: LongKey, defaultValue: Long = 0, suffix: String = ""): Long =
            sharedPreferences.getLong(key.name + suffix, defaultValue)

    /**
     * Checks if the key is present currently
     *
     * @param key
     */
    fun <T> contains(key: T, suffix: String = "") where T : Enum<T>, T : StorageKey = sharedPreferences.contains(key.name + suffix)

    /**
     * Removes this key from storage.
     *
     * @param key
     *
     * @return Instance of SimpleStorage for further use
     */
    fun <T> remove(key: T, suffix: String = ""): SimpleStorage where T : Enum<T>, T : StorageKey {
        sharedPreferences.edit().remove(key.name + suffix).apply()
        return this
    }

    @SuppressLint("ApplySharedPref")
    fun clear() {
        sharedPreferences.edit().clear().commit()
    }
}
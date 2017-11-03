package si.lanisnik.restaurantorder.cache.util

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmQuery

/**
 * Helper function for getting Realm instance
 */
internal fun getRealm(): Realm = Realm.getDefaultInstance()

/**
 * Utility extension for modifying database. Create a transaction, run the function passed as argument,
 * commit transaction and close realm instance.
 */
fun Realm.transaction(action: (Realm) -> Unit) {
    use {
        executeTransaction {
            action(this)
        }
    }
}

/**
 * Finds the first object of this class.
 *
 * @return First object or null if not present
 */
inline fun <reified T : RealmObject> Realm.findFirst(): T? = where(T::class.java).findFirst()

/**
 * Avoids explicitly stating the class.
 */
inline fun <reified E : RealmObject> Realm.where(): RealmQuery<E> = where(E::class.java)

inline fun <reified T : RealmObject> Realm.contains(): Boolean {
    use {
        return where(T::class.java).count() > 0
    }
}
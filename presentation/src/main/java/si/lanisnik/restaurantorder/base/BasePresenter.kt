package si.lanisnik.restaurantorder.base

/**
 * Interface for all MVP presenters to implement.
 */
interface BasePresenter<in T : BaseView> {
    fun setView(view: T)

    fun onStart()
    fun onStop()
}

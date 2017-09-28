package si.lanisnik.restaurantorder.internal.execution

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MainThread (UI Thread) implementation based on a [Scheduler]
 * which will execute actions on the Android UI thread.
 */
@Singleton
class MainThread @Inject constructor() : PostExecutionThread {
    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
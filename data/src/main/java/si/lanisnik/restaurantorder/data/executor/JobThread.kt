package si.lanisnik.restaurantorder.data.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [Scheduler] on which all background tasks will be executed.
 */
@Singleton
class JobThread @Inject constructor() : JobExecutionThread {
    override fun getScheduler(): Scheduler = Schedulers.newThread()
}
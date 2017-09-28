package si.lanisnik.restaurantorder.domain.executor

import io.reactivex.Scheduler

/**
 * Created by Domen Lanišnik on 25/09/2017.
 * domen.lanisnik@gmail.com
 */
interface ExecutionThread {
    fun getScheduler(): Scheduler
}
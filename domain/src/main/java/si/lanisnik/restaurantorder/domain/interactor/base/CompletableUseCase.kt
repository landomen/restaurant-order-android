package si.lanisnik.restaurantorder.domain.interactor.base

import io.reactivex.Completable
import io.reactivex.disposables.Disposables
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread

/**
 * Abstract class for a UseCase that returns an instance of a [Completable].
 */
abstract class CompletableUseCase<in Params>(
        private val jobExecutionThread: JobExecutionThread,
        private val postExecutionThread: PostExecutionThread) {

    private var subscription = Disposables.empty()

    /**
     * Builds a [Completable] which will be used when the current [CompletableUseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(params: Params): Completable

    /**
     * Executes the current use case.
     *
     * @param onComplete
     * @param onError
     * @param params Parameters used to build/execute this use case.
     */
    fun execute(onComplete: Action, onError: Consumer<Throwable>, params: Params) {
        subscription = this.buildUseCaseObservable(params)
                .subscribeOn(jobExecutionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(onComplete, onError)
    }

    fun unsubscribe() {
        if (!subscription.isDisposed)
            subscription.dispose()
    }
}
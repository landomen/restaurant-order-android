package si.lanisnik.restaurantorder.domain.interactor.base

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread

/**
 * Similar to [UseCase] but without any parameters.
 */
abstract class ParameterlessUseCase<Result>(
        private val jobExecutionThread: JobExecutionThread,
        private val postExecutionThread: PostExecutionThread,
        private val disposables: CompositeDisposable) {

    /**
     * Builds an [io.reactivex.Observable] which will be used when executing the current {@link UseCase}.
     */
    abstract fun buildUseCaseFlowable(): Flowable<Result>

    /**
     * Executes the current use case.
     *
     * @param subscriber [ResourceSubscriber] which will be listening to the flowable build
     * by [buildUseCaseFlowable] method.
     * @param parameters Parameters used to build/execute this use case.
     */
    fun execute(subscriber: ResourceSubscriber<Result>) {
        val disposable = this.buildUseCaseFlowable()
                .subscribeOn(jobExecutionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(subscriber)
        addDisposable(disposable)
    }


    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed)
            disposables.dispose()
    }

    /**
     * Adds current disposable for later disposal.
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}
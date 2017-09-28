package si.lanisnik.restaurantorder.domain.interactor.base

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link DisposableObserver}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<Result, in Parameters>(
        private val jobExecutionThread: JobExecutionThread,
        private val postExecutionThread: PostExecutionThread) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [io.reactivex.Flowable] which will be used when executing the current {@link UseCase}.
     */
    abstract fun buildUseCaseFlowable(parameters: Parameters): Flowable<Result>

    /**
     * Executes the current use case.
     *
     * @param subscriber [ResourceSubscriber] which will be listening to the flowable build
     * by [buildUseCaseFlowable] method.
     * @param parameters Parameters used to build/execute this use case.
     */
    fun execute(subscriber: ResourceSubscriber<Result>, parameters: Parameters) {
        val disposable = this.buildUseCaseFlowable(parameters)
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
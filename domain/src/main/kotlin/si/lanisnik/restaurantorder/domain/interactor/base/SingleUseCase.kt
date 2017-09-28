package si.lanisnik.restaurantorder.domain.interactor.base

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.ResourceSubscriber
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread

/**
 * Created by Domen Lanišnik on 27/09/2017.
 * domen.lanisnik@gmail.com
 */
abstract class SingleUseCase<Result, in Params>(
        private val jobExecutionThread: JobExecutionThread,
        private val postExecutionThread: PostExecutionThread) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Single] which will be used when executing the current [SingleUseCase].
     */
    abstract fun buildUseCaseObservable(params: Params? = null): Single<Result>

    /**
     * Executes the current use case.
     *
     * @param subscriber [ResourceSubscriber] which will be listening to the flowable build
     * by [buildUseCaseFlowable] method.
     * @param parameters Parameters used to build/execute this use case.
     */
    fun execute(singleObserver: DisposableSingleObserver<Result>, params: Params? = null) {
        val disposable = this.buildUseCaseObservable(params)
                .subscribeOn(jobExecutionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(singleObserver)
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
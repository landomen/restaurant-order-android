package si.lanisnik.restaurantorder.domain.interactor.base

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
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
    abstract protected fun buildUseCaseObservable(params: Params? = null): Single<Result>

    /**
     * Executes the current use case.
     *
     * @param onSuccess
     * @param onError
     * @param params Parameters used to build/execute this use case.
     */
    fun execute(onSuccess: Consumer<Result>, onError: Consumer<Throwable>, params: Params? = null) {
        val disposable = this.buildUseCaseObservable(params)
                .subscribeOn(jobExecutionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(onSuccess, onError)
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
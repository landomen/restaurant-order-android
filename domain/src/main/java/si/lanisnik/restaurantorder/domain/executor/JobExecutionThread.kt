package si.lanisnik.restaurantorder.domain.executor

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link com.fernandocejas.android10.sample.domain.interactor.UseCase} out of the UI thread.
 */
interface JobExecutionThread : ExecutionThread
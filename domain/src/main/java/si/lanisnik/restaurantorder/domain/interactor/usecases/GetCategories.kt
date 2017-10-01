package si.lanisnik.restaurantorder.domain.interactor.usecases

import io.reactivex.Flowable
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.FlowableUseCase
import si.lanisnik.restaurantorder.domain.model.foodcategory.FoodCategory
import si.lanisnik.restaurantorder.domain.repository.FoodCategoryRepository
import javax.inject.Inject

/**
 * Retrieves a collection of all [FoodCategory].
 */
class GetCategories @Inject constructor(
        private val categoryRepository: FoodCategoryRepository,
        threadExecutor: JobExecutionThread,
        postExecutionThread: PostExecutionThread) :
        FlowableUseCase<List<FoodCategory>, Nothing>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Flowable<List<FoodCategory>> {
        return categoryRepository.getCategories()
    }

}
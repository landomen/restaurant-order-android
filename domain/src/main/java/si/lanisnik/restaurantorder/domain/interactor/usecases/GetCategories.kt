package si.lanisnik.restaurantorder.domain.interactor.usecases

import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.SingleUseCase
import si.lanisnik.restaurantorder.domain.model.menuitem.FoodCategory
import si.lanisnik.restaurantorder.domain.repository.MenuItemRepository
import javax.inject.Inject

/**
 * Retrieves a collection of all [FoodCategory].
 */
class GetCategories @Inject constructor(
        private val menuItemRepository: MenuItemRepository,
        threadExecutor: JobExecutionThread,
        postExecutionThread: PostExecutionThread) :
        SingleUseCase<List<FoodCategory>, Nothing>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Single<List<FoodCategory>> =
            menuItemRepository.getCategories()

}
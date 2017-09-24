package si.lanisnik.restaurantorder.domain.interactor.usecases

import io.reactivex.Flowable
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.executor.ThreadExecutor
import si.lanisnik.restaurantorder.domain.interactor.base.UseCase
import si.lanisnik.restaurantorder.domain.model.menuitem.FoodCategory
import si.lanisnik.restaurantorder.domain.repository.MenuItemRepository
import javax.inject.Inject

/**
 * Retrieves a collection of all [FoodCategory].
 */
class GetCategories @Inject constructor(
        private val menuItemRepository: MenuItemRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) :
        UseCase<List<FoodCategory>, Nothing>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseFlowable(parameters: Nothing): Flowable<List<FoodCategory>> =
            menuItemRepository.getCategories()

}
package si.lanisnik.restaurantorder.domain.interactor.menuitem

import io.reactivex.Flowable
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.interactor.base.FlowableUseCase
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import si.lanisnik.restaurantorder.domain.repository.MenuItemRepository
import javax.inject.Inject

/**
 * Retrieves a collection of all [MenuItem] for the selected category.
 */
class GetMenuItems @Inject constructor(private val menuItemRepository: MenuItemRepository,
                                       threadExecutor: JobExecutionThread,
                                       postExecutionThread: PostExecutionThread) :
        FlowableUseCase<List<MenuItem>, Int>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(categoryId: Int?): Flowable<List<MenuItem>> =
            menuItemRepository.getMenuItems(categoryId!!)
}
package si.lanisnik.restaurantorder.data

import io.reactivex.Completable
import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.remote.model.foodcategory.FoodCategoryDto
import si.lanisnik.restaurantorder.data.remote.service.FoodCategoriesService
import si.lanisnik.restaurantorder.domain.model.foodcategory.FoodCategory
import si.lanisnik.restaurantorder.domain.repository.FoodCategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Domen Lanišnik on 29/09/2017.
 * domen.lanisnik@gmail.com
 */
@Singleton
class FoodCategoryDataRepository @Inject constructor(private val categoriesService: FoodCategoriesService) : FoodCategoryRepository {

    override fun getCategories(): Flowable<List<FoodCategory>> {
        return categoriesService.getFoodCategories()
                .flatMap { categoriesResponse -> Flowable.just(categoriesResponse.categories) }
                .map { list: List<FoodCategoryDto> -> list.map { FoodCategory(it.id, it.title) } }
    }

    override fun saveCategories(categories: List<FoodCategory>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
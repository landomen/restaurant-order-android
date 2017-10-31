package si.lanisnik.restaurantorder.mapper

import si.lanisnik.restaurantorder.domain.model.foodcategory.FoodCategory
import si.lanisnik.restaurantorder.ui.foodcategory.model.FoodCategoryModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/10/2017.
 * domen.lanisnik@gmail.com
 */
class FoodCategoryMapper @Inject constructor() : PresentationMapper<FoodCategory, FoodCategoryModel> {

    override fun mapToModel(model: FoodCategory): FoodCategoryModel = FoodCategoryModel(model.id, model.title)

}
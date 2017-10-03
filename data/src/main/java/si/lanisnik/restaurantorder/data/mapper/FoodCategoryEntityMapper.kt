package si.lanisnik.restaurantorder.data.mapper

import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity
import si.lanisnik.restaurantorder.domain.model.foodcategory.FoodCategory
import javax.inject.Inject

/**
 * Map a [FoodCategoryEntity] to and from a [FoodCategory] instance when data is moving between
 * this layer and the Domain layer.
 */
class FoodCategoryEntityMapper @Inject constructor() : EntityMapper<FoodCategoryEntity, FoodCategory> {

    override fun mapFromEntity(model: FoodCategoryEntity): FoodCategory = FoodCategory(model.id, model.title)

    override fun mapToEntity(model: FoodCategory): FoodCategoryEntity = FoodCategoryEntity(model.id, model.title)
}
package si.lanisnik.restaurantorder.remote.mapper

import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity
import si.lanisnik.restaurantorder.remote.model.foodcategory.FoodCategoryDto
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 29/09/2017.
 * domen.lanisnik@gmail.com
 */
class FoodCategoryRemoteMapper @Inject constructor() : RemoteEntityMapper<FoodCategoryDto, FoodCategoryEntity> {

    override fun mapFromRemote(remoteModel: FoodCategoryDto): FoodCategoryEntity =
            FoodCategoryEntity(remoteModel.id, remoteModel.title)
}
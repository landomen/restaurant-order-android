package si.lanisnik.restaurantorder.data.remote.service

import io.reactivex.Flowable
import retrofit2.http.GET
import si.lanisnik.restaurantorder.data.remote.model.foodcategory.CategoriesResponse

/**
 * Created by Domen Lanišnik on 29/09/2017.
 * domen.lanisnik@gmail.com
 */
interface FoodCategoriesService {

    @GET("menu-items/categories")
    fun getFoodCategories(): Flowable<CategoriesResponse>

}
package si.lanisnik.restaurantorder.domain.model.order

import io.reactivex.subjects.BehaviorSubject
import javax.inject.Singleton

/**
 * Created by Domen Lanišnik on 10/11/2017.
 * domen.lanisnik@gmail.com
 */
@Singleton
class ShoppingCart {
    private val selectedMenuItems = mutableListOf<SelectedMenuItem>()
    private var totalCount: Int = 0
    val totalCountSubject = BehaviorSubject.create<Int>()

    fun addItem(id: Int) {
        val i = indexOfItem(id)
        if (i != -1)
            selectedMenuItems[i].count += 1
        else
            selectedMenuItems.add(SelectedMenuItem(id, 1, null))
        updateTotalCount()
    }

    fun updateItem(id: Int, count: Int, comment: String) {
        with(selectedMenuItems[indexOfItem(id)]) {
            this.count = count
            this.comment = if (comment.isEmpty())
                null
            else
                comment
        }
        updateTotalCount()
    }

    fun deleteItem(id: Int) {
        selectedMenuItems.removeAt(indexOfItem(id))
        updateTotalCount()
    }

    private fun indexOfItem(id: Int): Int = selectedMenuItems.indexOfFirst { it.id == id }

    private fun updateTotalCount() {
        totalCount = selectedMenuItems.sumBy { it.count }
        totalCountSubject.onNext(totalCount)
    }


}
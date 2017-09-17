package si.lanisnik.restaurantorder.presentation.categories

import android.os.Bundle
import android.support.annotation.Nullable
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.presentation.base.BaseActivity

class CategoriesListActivity : BaseActivity() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_list)
    }
}

package training.androidkotlin.shoppinglist.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import training.androidkotlin.shoppinglist.DBUtil.App
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithCategories
import training.androidkotlin.shoppinglist.Repository.ShoppingListWithCategoriesRepository

class ShoppingListWithCategoriesViewModel: ViewModel() {
    private var shoppingListWithCategoriesRepository: ShoppingListWithCategoriesRepository

    init {
        val dao = App.database.shoppingListWithCategoriesDao()
        shoppingListWithCategoriesRepository = ShoppingListWithCategoriesRepository(dao)
    }

    fun getShoppingListWithCategories(shoppingListId: Long): LiveData<ShoppingListWithCategories> {
        return shoppingListWithCategoriesRepository.getShoppingListWithCategories(shoppingListId)
    }
}
package training.androidkotlin.shoppinglist.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import training.androidkotlin.shoppinglist.DBUtil.App
import training.androidkotlin.shoppinglist.Entity.Relation.CategoryWithShoppingLists
import training.androidkotlin.shoppinglist.Repository.CategoryWithShoppingListsRepository

class CategoryWithShoppingListsViewModel: ViewModel() {
    private var categoryWithShoppingListsRepository: CategoryWithShoppingListsRepository

    init {
        val dao = App.database.categoryWithShoppingListsDao()
        categoryWithShoppingListsRepository = CategoryWithShoppingListsRepository(dao)
    }

    fun getCategoryWithShoppingLists(categoryId: Long): LiveData<CategoryWithShoppingLists> {
        return categoryWithShoppingListsRepository.getCategoryWithShoppingLists(categoryId)
    }
}
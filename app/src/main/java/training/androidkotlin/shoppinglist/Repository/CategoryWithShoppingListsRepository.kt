package training.androidkotlin.shoppinglist.Repository

import androidx.lifecycle.LiveData
import training.androidkotlin.shoppinglist.DAO.CategoryWithShoppingListsDao
import training.androidkotlin.shoppinglist.Entity.Relation.CategoryWithShoppingLists

class CategoryWithShoppingListsRepository(
    private val categoryWithShoppingListsDao: CategoryWithShoppingListsDao
) {
    fun getCategoryWithShoppingLists(categoryId: Long): LiveData<CategoryWithShoppingLists> {
        return categoryWithShoppingListsDao.getCategoryWithShoppingLists(categoryId)
    }
}
package training.androidkotlin.shoppinglist.Repository

import androidx.lifecycle.LiveData
import training.androidkotlin.shoppinglist.DAO.ShoppingListWithCategoriesDao
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithCategories

class ShoppingListWithCategoriesRepository(
    private val shoppingListWithCategoriesDao: ShoppingListWithCategoriesDao
) {
    fun getShoppingListWithCategories(shoppingListId: Long): LiveData<ShoppingListWithCategories> {
        return shoppingListWithCategoriesDao.getShoppingListWithCategories(shoppingListId)
    }
}
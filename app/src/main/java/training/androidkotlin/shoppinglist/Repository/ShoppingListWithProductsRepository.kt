package training.androidkotlin.shoppinglist.Repository

import androidx.lifecycle.LiveData
import training.androidkotlin.shoppinglist.DAO.ShoppingListWithProductsDao
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithProducts

class ShoppingListWithProductsRepository(private val shoppingListWithProductsDao: ShoppingListWithProductsDao) {
    fun getShoppingListsWithProducts() : LiveData<List<ShoppingListWithProducts>> {
        return shoppingListWithProductsDao.getShoppingListsWithProducts()
    }
}
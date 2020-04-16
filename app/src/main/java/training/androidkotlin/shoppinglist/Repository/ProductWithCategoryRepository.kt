package training.androidkotlin.shoppinglist.Repository

import androidx.lifecycle.LiveData
import training.androidkotlin.shoppinglist.DAO.ProductWithCategoryDao
import training.androidkotlin.shoppinglist.DAO.ShoppingListDao
import training.androidkotlin.shoppinglist.DAO.ShoppingListWithProductsDao
import training.androidkotlin.shoppinglist.Entity.Relation.ProductWithCategory
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithProducts
import training.androidkotlin.shoppinglist.Entity.ShoppingList

class ProductWithCategoryRepository(private val productWithCategoryDao: ProductWithCategoryDao) {
    fun getProductsWithCategory(shoppingListId: Long) : LiveData<List<ProductWithCategory>> {
        return productWithCategoryDao.getProductsWithCategory(shoppingListId)
    }
}
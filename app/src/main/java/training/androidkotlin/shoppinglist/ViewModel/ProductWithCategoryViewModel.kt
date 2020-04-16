package training.androidkotlin.shoppinglist.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import training.androidkotlin.shoppinglist.DAO.ProductWithCategoryDao
import training.androidkotlin.shoppinglist.DAO.ShoppingListDao
import training.androidkotlin.shoppinglist.DAO.ShoppingListWithProductsDao
import training.androidkotlin.shoppinglist.DBUtil.App
import training.androidkotlin.shoppinglist.DBUtil.AppDatabase
import training.androidkotlin.shoppinglist.Entity.Relation.ProductWithCategory
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithProducts
import training.androidkotlin.shoppinglist.Entity.ShoppingList
import training.androidkotlin.shoppinglist.Repository.ProductWithCategoryRepository
import training.androidkotlin.shoppinglist.Repository.ShoppingListRepository
import training.androidkotlin.shoppinglist.Repository.ShoppingListWithProductsRepository

class ProductWithCategoryViewModel : ViewModel() {
    private var productWithCategoryRepository: ProductWithCategoryRepository

    init {
        val productWithCategoryDao: ProductWithCategoryDao = App.database.productWithCategoryDao()
        productWithCategoryRepository = ProductWithCategoryRepository(productWithCategoryDao)
    }

    fun getProducts(shoppingListId: Long) : LiveData<List<ProductWithCategory>>
    {
        return productWithCategoryRepository.getProductsWithCategory(shoppingListId)
    }
}
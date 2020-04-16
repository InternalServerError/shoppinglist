package training.androidkotlin.shoppinglist.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import training.androidkotlin.shoppinglist.DAO.ShoppingListDao
import training.androidkotlin.shoppinglist.DAO.ShoppingListWithProductsDao
import training.androidkotlin.shoppinglist.DBUtil.App
import training.androidkotlin.shoppinglist.DBUtil.AppDatabase
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithProducts
import training.androidkotlin.shoppinglist.Entity.ShoppingList
import training.androidkotlin.shoppinglist.Repository.ShoppingListRepository
import training.androidkotlin.shoppinglist.Repository.ShoppingListWithProductsRepository

class ShoppingListWithProductsViewModel : ViewModel() {
    private var shoppingListWithProductsRepository: ShoppingListWithProductsRepository

    init {
        val shoppingListWithProductsDao: ShoppingListWithProductsDao = App.database.shoppingListWithProductsDao()
        shoppingListWithProductsRepository = ShoppingListWithProductsRepository(shoppingListWithProductsDao)
    }

    fun getShoppingLists() : LiveData<List<ShoppingListWithProducts>>
    {
        return shoppingListWithProductsRepository.getShoppingListsWithProducts()
    }
}
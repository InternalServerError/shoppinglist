package training.androidkotlin.shoppinglist.ViewModel

import androidx.lifecycle.ViewModel
import training.androidkotlin.shoppinglist.DAO.ShoppingListDao
import training.androidkotlin.shoppinglist.DBUtil.App
import training.androidkotlin.shoppinglist.Entity.ShoppingList
import training.androidkotlin.shoppinglist.Repository.ShoppingListRepository

class ShoppingListViewModel : ViewModel() {
    private var shoppingListRepository: ShoppingListRepository

    init {
        val shoppingListDao: ShoppingListDao = App.database.shoppingListDao()
        shoppingListRepository = ShoppingListRepository(shoppingListDao)
    }

    fun addNewShoppingList(shoppingList: ShoppingList) : Long
    {
        return shoppingListRepository.addNewShoppingList(shoppingList)
    }
}
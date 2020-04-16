package training.androidkotlin.shoppinglist.Repository

import training.androidkotlin.shoppinglist.DAO.ShoppingListDao
import training.androidkotlin.shoppinglist.Entity.ShoppingList

class ShoppingListRepository(val shoppingListDao: ShoppingListDao) {
    fun addNewShoppingList(shoppingList: ShoppingList): Long {
        return shoppingListDao.insertNewShoppingList(shoppingList)
    }
}
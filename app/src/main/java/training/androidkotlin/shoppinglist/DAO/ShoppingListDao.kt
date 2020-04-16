package training.androidkotlin.shoppinglist.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import training.androidkotlin.shoppinglist.Entity.ShoppingList

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertNewShoppingList(shoppingList: ShoppingList): Long

    @Update
    fun updateShoppingList(shoppingList: ShoppingList)

    @Delete
    fun deleteShoppingList(shoppingList: ShoppingList)
}
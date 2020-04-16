package training.androidkotlin.shoppinglist.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithCategories

@Dao
interface ShoppingListWithCategoriesDao {
    @Transaction
    @Query("SELECT * FROM shopping_list WHERE shoppingListId = :shoppingListId")
    fun getShoppingListWithCategories(shoppingListId: Long): LiveData<ShoppingListWithCategories>
}
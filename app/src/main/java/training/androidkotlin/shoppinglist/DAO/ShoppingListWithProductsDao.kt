package training.androidkotlin.shoppinglist.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithProducts

@Dao
interface ShoppingListWithProductsDao {
    @Transaction
    @Query("SELECT * FROM shopping_list WHERE active = 1")
    fun getShoppingListsWithProducts(): LiveData<List<ShoppingListWithProducts>>
}
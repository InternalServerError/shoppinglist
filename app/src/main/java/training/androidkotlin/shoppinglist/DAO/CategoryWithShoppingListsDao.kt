package training.androidkotlin.shoppinglist.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import training.androidkotlin.shoppinglist.Entity.Relation.CategoryWithShoppingLists

@Dao
interface CategoryWithShoppingListsDao {
    @Transaction
    @Query("SELECT * FROM category WHERE categoryId = :categoryId")
    fun getCategoryWithShoppingLists(categoryId: Long): LiveData<CategoryWithShoppingLists>
}
package training.androidkotlin.shoppinglist.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import training.androidkotlin.shoppinglist.Entity.Relation.ProductWithCategory
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithProducts

@Dao
interface ProductWithCategoryDao {
    @Transaction
    @Query("SELECT * FROM product WHERE shoppingListOwnerId = :shoppingListId")
    fun getProductsWithCategory(shoppingListId: Long): LiveData<List<ProductWithCategory>>
}
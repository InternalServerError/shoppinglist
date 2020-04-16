package training.androidkotlin.shoppinglist.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import training.androidkotlin.shoppinglist.Entity.Product
import training.androidkotlin.shoppinglist.Entity.ShoppingList

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertNewProduct(product: Product): Long

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)
}
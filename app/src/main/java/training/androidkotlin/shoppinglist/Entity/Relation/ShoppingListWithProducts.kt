package training.androidkotlin.shoppinglist.Entity.Relation

import androidx.room.Relation
import training.androidkotlin.shoppinglist.Entity.Product
import java.time.LocalDateTime

data class ShoppingListWithProducts(
    val shoppingListId: Long,
    val title: String,
    val createdAt: String = LocalDateTime.now().toString(),
    val updatedAt: String? = null,
    val active: Boolean = true,
    val shoppingListOwnerId : Long? = null,
    val categoryOwnerId : Long? = null,
    @Relation(parentColumn = "shoppingListId", entityColumn = "shoppingListOwnerId")
    val products: List<Product>?
)
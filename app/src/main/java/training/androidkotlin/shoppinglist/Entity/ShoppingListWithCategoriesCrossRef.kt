package training.androidkotlin.shoppinglist.Entity

import androidx.room.Entity

@Entity(primaryKeys = ["shoppingListId", "categoryId"])
data class ShoppingListWithCategoriesCrossRef(
    val shoppingListId: Long,
    val categoryId: Long
)
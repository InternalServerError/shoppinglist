package training.androidkotlin.shoppinglist.Entity.Relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import training.androidkotlin.shoppinglist.Entity.Category
import training.androidkotlin.shoppinglist.Entity.ShoppingList
import training.androidkotlin.shoppinglist.Entity.ShoppingListWithCategoriesCrossRef

data class CategoryWithShoppingLists(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "shoppingList",
        associateBy = Junction(ShoppingListWithCategoriesCrossRef::class)
    )
    val shoppingLists: List<ShoppingList>?
)
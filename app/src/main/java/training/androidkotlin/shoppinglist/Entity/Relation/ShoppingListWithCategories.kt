package training.androidkotlin.shoppinglist.Entity.Relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import training.androidkotlin.shoppinglist.Entity.Category
import training.androidkotlin.shoppinglist.Entity.ShoppingList
import training.androidkotlin.shoppinglist.Entity.ShoppingListWithCategoriesCrossRef

data class ShoppingListWithCategories(
    @Embedded val shoppingList: ShoppingList,
    @Relation(
        parentColumn = "shoppingList",
        entityColumn = "categoryId",
        associateBy = Junction(ShoppingListWithCategoriesCrossRef::class)
    )
    val categories: List<Category>?
)
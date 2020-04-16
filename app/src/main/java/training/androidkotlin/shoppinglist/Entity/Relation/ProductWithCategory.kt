package training.androidkotlin.shoppinglist.Entity.Relation

import androidx.room.Embedded
import androidx.room.Relation
import training.androidkotlin.shoppinglist.Entity.Category
import training.androidkotlin.shoppinglist.Entity.Product
import java.time.LocalDateTime

data class ProductWithCategory(
    @Embedded val product: Product,
    @Relation(parentColumn = "categoryOwnerId", entityColumn = "categoryId")
    val category: Category?
)
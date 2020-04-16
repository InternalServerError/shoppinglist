package training.androidkotlin.shoppinglist.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "shopping_list")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    var shoppingListId: Long,
    var title : String,
    var createdAt: String = LocalDateTime.now().toString(),
    var updatedAt: String? = null,
    var active: Boolean = true
)
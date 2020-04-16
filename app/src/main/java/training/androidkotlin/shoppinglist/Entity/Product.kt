package training.androidkotlin.shoppinglist.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var productId: Long,
    var name : String,
    var quantity : Int = 1,
    var shoppingListOwnerId : Long? = null,
    var categoryOwnerId : Long? = null
)
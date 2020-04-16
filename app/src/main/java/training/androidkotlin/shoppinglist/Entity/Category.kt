package training.androidkotlin.shoppinglist.Entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "category",
    indices = [
        Index(value = ["name"],
            unique = true)
    ]
)
data class Category(
    @PrimaryKey(autoGenerate = true)
    var categoryId: Long,
    var name: String
)
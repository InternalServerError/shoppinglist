package training.androidkotlin.shoppinglist.DBUtil

import androidx.room.Database
import androidx.room.RoomDatabase
import training.androidkotlin.shoppinglist.DAO.*
import training.androidkotlin.shoppinglist.Entity.Category
import training.androidkotlin.shoppinglist.Entity.Product
import training.androidkotlin.shoppinglist.Entity.ShoppingList

@Database(
    entities = [
        ShoppingList::class,
        Product::class,
        Category::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shoppingListDao() : ShoppingListDao
    abstract fun productDao() : ProductDao
    abstract fun categoryDao() : CategoryDao
    abstract fun shoppingListWithProductsDao() : ShoppingListWithProductsDao
    abstract fun productWithCategoryDao() : ProductWithCategoryDao
    abstract fun shoppingListWithCategoriesDao() : ShoppingListWithCategoriesDao
    abstract fun categoryWithShoppingListsDao() : CategoryWithShoppingListsDao
}
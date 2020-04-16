package training.androidkotlin.shoppinglist.Repository

import androidx.lifecycle.LiveData
import training.androidkotlin.shoppinglist.DAO.CategoryDao
import training.androidkotlin.shoppinglist.Entity.Category

class CategoryRepository(private val categoryDao: CategoryDao) {
    fun addNewCategory(category: Category): Long {
        return categoryDao.insertNewCategory(category)
    }

    fun getCategories(): LiveData<List<Category>> {
        return categoryDao.getCategories()
    }

    fun getCategoryByName(name: String): Category? {
        return categoryDao.getCategoryByName(name)
    }
}
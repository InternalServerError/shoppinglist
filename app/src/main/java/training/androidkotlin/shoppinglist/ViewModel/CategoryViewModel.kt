package training.androidkotlin.shoppinglist.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import training.androidkotlin.shoppinglist.DAO.CategoryDao
import training.androidkotlin.shoppinglist.DAO.ProductDao
import training.androidkotlin.shoppinglist.DBUtil.App
import training.androidkotlin.shoppinglist.Entity.Category
import training.androidkotlin.shoppinglist.Entity.Product
import training.androidkotlin.shoppinglist.Repository.CategoryRepository
import training.androidkotlin.shoppinglist.Repository.ProductRepository

class CategoryViewModel : ViewModel() {
    private var categoryRepository: CategoryRepository

    init {
        val categoryDao: CategoryDao = App.database.categoryDao()
        categoryRepository = CategoryRepository(categoryDao)
    }

    fun addNewCategory(category: Category) : Long {
        return categoryRepository.addNewCategory(category)
    }

    fun getCategories(): LiveData<List<Category>> {
        return categoryRepository.getCategories()
    }

    fun getCategoryByName(name: String): Category? {
        return categoryRepository.getCategoryByName(name)
    }
}
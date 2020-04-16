package training.androidkotlin.shoppinglist.ViewModel

import androidx.lifecycle.ViewModel
import training.androidkotlin.shoppinglist.DAO.ProductDao
import training.androidkotlin.shoppinglist.DBUtil.App
import training.androidkotlin.shoppinglist.Entity.Product
import training.androidkotlin.shoppinglist.Repository.ProductRepository

class ProductViewModel : ViewModel() {
    private var productRepository: ProductRepository

    init {
        val productDao: ProductDao = App.database.productDao()
        productRepository = ProductRepository(productDao)
    }

    fun addNewProduct(product: Product) : Long
    {
        return productRepository.addNewProduct(product)
    }
}
package training.androidkotlin.shoppinglist.Repository

import training.androidkotlin.shoppinglist.DAO.ProductDao
import training.androidkotlin.shoppinglist.Entity.Product

class ProductRepository(val productDao: ProductDao) {
    fun addNewProduct(product: Product): Long {
        return productDao.insertNewProduct(product)
    }
}
package training.androidkotlin.shoppinglist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.collection.ArrayMap
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.shopping_list_add_fragment.*
import training.androidkotlin.shoppinglist.Adapter.CategoryRecyclerViewAdapter
import training.androidkotlin.shoppinglist.Dialog.AddNewCategoryDialog
import training.androidkotlin.shoppinglist.Dialog.AddProductToCategoryDialog
import training.androidkotlin.shoppinglist.Entity.Category
import training.androidkotlin.shoppinglist.Entity.Product
import training.androidkotlin.shoppinglist.Entity.ShoppingList
import training.androidkotlin.shoppinglist.ViewModel.CategoryViewModel
import training.androidkotlin.shoppinglist.ViewModel.ProductViewModel
import training.androidkotlin.shoppinglist.ViewModel.ShoppingListViewModel
import java.util.concurrent.Executors

class AddShoppingListFragment : Fragment(), View.OnClickListener {
    private lateinit var rootView: View
    private lateinit var categoryRecyclerviewAdapter: CategoryRecyclerViewAdapter

    private val categoryViewModel: CategoryViewModel = CategoryViewModel()
    private val products: MutableMap<String, MutableList<Product>> = mutableMapOf()
    private val categories: MutableList<Category> = mutableListOf()
    private val categoriesAsString: MutableList<String> = emptyList<String>().toMutableList()

    companion object {
        const val TARGET_FRAGMENT_NEW_CATEGORY_REQUEST_CODE = 1
        const val TARGET_FRAGMENT_NEW_PRODUCT_CATEGORY_REQUEST_CODE = 2
        const val EXTRA_CATEGORY_NAME = "category_name"
        const val EXTRA_CATEGORY_ID = "category_id"
        const val EXTRA_PRODUCT_CATEGORY_NAME = "product_category_name"
        const val EXTRA_PRODUCT_CATEGORY_QUANTITY = "product_category_quantity"

        fun newCategoryIntent(categoryName: String): Intent {
            val intent = Intent()
            intent.putExtra(EXTRA_CATEGORY_NAME, categoryName)
            return intent
        }

        fun newProductCategoryIntent(categoryName: String, productName: String, quantity: Int): Intent {
            val intent = Intent()
            intent.putExtra(EXTRA_CATEGORY_ID, categoryName)
            intent.putExtra(EXTRA_PRODUCT_CATEGORY_NAME, productName)
            intent.putExtra(EXTRA_PRODUCT_CATEGORY_QUANTITY, quantity)
            return intent
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = resources.getString(R.string.add_shopping_list)
        rootView = inflater.inflate(R.layout.shopping_list_add_fragment, container, false)
        categoryViewModel.getCategories().observe(viewLifecycleOwner, Observer {
            transformCategoryList(it)
        })
        categoryRecyclerviewAdapter = CategoryRecyclerViewAdapter(categories, products, this@AddShoppingListFragment)

        rootView.findViewById<RecyclerView>(R.id.new_shopping_list_categories_recycler_view).apply {
            adapter = categoryRecyclerviewAdapter
            layoutManager = LinearLayoutManager(this@AddShoppingListFragment.context)
            setHasFixedSize(true)
        }

        val addCategoryButton: ImageButton = rootView.findViewById(R.id.new_shopping_list_categories_add_category_button)
        addCategoryButton.setOnClickListener {
            showAddCategoryDialog()
        }

        return rootView
    }

    override fun onClick(v: View?) {
        v?.let {
            showAddProductToCategoryDialog(it.tag as String)
        }
    }

    private fun showAddCategoryDialog() {
        val addNewCategoryDialog = AddNewCategoryDialog(categoriesAsString)
        addNewCategoryDialog.setTargetFragment(this, TARGET_FRAGMENT_NEW_CATEGORY_REQUEST_CODE)
        addNewCategoryDialog.listener = object: AddNewCategoryDialog.AddNewCategoryDialogListener {
            override fun onCancelButtonClick() {}
        }
        activity?.supportFragmentManager?.let { addNewCategoryDialog.show(it, "AddNewCategoryDialog") }
    }

    private fun showAddProductToCategoryDialog(categoryName: String) {
        val addProductToCategoryDialog = AddProductToCategoryDialog(categoryName)
        addProductToCategoryDialog.setTargetFragment(this, TARGET_FRAGMENT_NEW_PRODUCT_CATEGORY_REQUEST_CODE)
        activity?.supportFragmentManager?.let { addProductToCategoryDialog.show(it, "AddProductToCategoryDialog") }
    }

    private fun addCategory(categoryName: String) {
        val category = Category(0, categoryName)
        categories.add(category)
        Executors.newSingleThreadExecutor().execute {
            categoryViewModel.addNewCategory(category)
        }
        categoryRecyclerviewAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode) {
            TARGET_FRAGMENT_NEW_CATEGORY_REQUEST_CODE -> {
                val categoryName: String = data?.getStringExtra(EXTRA_CATEGORY_NAME)!!
                addCategory(categoryName)
            }
            TARGET_FRAGMENT_NEW_PRODUCT_CATEGORY_REQUEST_CODE -> {
                val categoryName: String? = data?.getStringExtra(EXTRA_CATEGORY_ID)
                val productName: String? = data?.getStringExtra(EXTRA_PRODUCT_CATEGORY_NAME)
                val productQuantity: Int = data?.getIntExtra(EXTRA_PRODUCT_CATEGORY_QUANTITY, 0)!!

                if (categoryName != null && categoryName.isNotEmpty() && productName != null && productName.isNotEmpty() && productQuantity != 0) {
                    val product = Product(0, productName, productQuantity)
                    var productsList = mutableListOf<Product>()
                    if (products.containsKey(categoryName)) {
                        productsList = products[categoryName]!!
                    }
                    productsList.add(product)
                    products[categoryName] = productsList
                    categoryRecyclerviewAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun transformCategoryList(list: List<Category>) {
        list.forEach {
            categoriesAsString.add(it.name)
        }
    }
}
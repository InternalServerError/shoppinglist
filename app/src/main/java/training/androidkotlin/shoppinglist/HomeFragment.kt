package training.androidkotlin.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import training.androidkotlin.shoppinglist.Adapter.ShoppingListAdapter
import training.androidkotlin.shoppinglist.Entity.Category
import training.androidkotlin.shoppinglist.Entity.Product
import training.androidkotlin.shoppinglist.Entity.ShoppingList
import training.androidkotlin.shoppinglist.ViewModel.CategoryViewModel
import training.androidkotlin.shoppinglist.ViewModel.ProductViewModel
import training.androidkotlin.shoppinglist.ViewModel.ShoppingListViewModel
import training.androidkotlin.shoppinglist.ViewModel.ShoppingListWithProductsViewModel
import java.util.concurrent.Executors

class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var rootView: View
    private val shoppingListViewModel: ShoppingListViewModel = ShoppingListViewModel()
    private val productViewModel: ProductViewModel = ProductViewModel()
    private val categoryViewModel: CategoryViewModel = CategoryViewModel()
    private val shoppingListWithProductsViewModel: ShoppingListWithProductsViewModel = ShoppingListWithProductsViewModel()

    private lateinit var shoppingListAdapter: ShoppingListAdapter
    private lateinit var view: LinearLayoutManager
    private lateinit var shoppingListsRecyclerView: RecyclerView

    init {
        Executors.newSingleThreadExecutor().execute {
            val shoppingList = ShoppingList(0, "Titre test")
            val shoppingListId = shoppingListViewModel.addNewShoppingList(shoppingList)

            val category = Category(0, "Category Test 1")
            val categoryId = categoryViewModel.addNewCategory(category)
            val category2 = Category(0, "Category Test 2")
            val category2Id = categoryViewModel.addNewCategory(category2)

            val product: Product = Product(0, "Product Test", 5, shoppingListId, categoryId)
            val productId: Long = productViewModel.addNewProduct(product)
            val productB: Product = Product(0, "Product Test 2", 5, shoppingListId, category2Id)
            val productBId: Long = productViewModel.addNewProduct(productB)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val listener = this
        shoppingListAdapter = ShoppingListAdapter(emptyList(), listener)
        view = LinearLayoutManager(this.context)

        activity?.title = resources.getString(R.string.my_shopping_lists)
        val fabButton = rootView.findViewById<FloatingActionButton>(R.id.create_shopping_list_fab)

        fabButton.setOnClickListener {
            val addNewShoppingListFragment = AddShoppingListFragment()

            activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, addNewShoppingListFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        shoppingListWithProductsViewModel.getShoppingLists().observe(viewLifecycleOwner, Observer { lists ->
            shoppingListsRecyclerView = rootView.findViewById<RecyclerView>(R.id.shopping_lists).apply {
                adapter = ShoppingListAdapter(lists, listener)
                layoutManager = view
            }
        })

        return rootView
    }

    override fun onClick(v: View?) {
        val productListFragment = ProductListFragment(v?.tag as Long?)
        activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, productListFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}
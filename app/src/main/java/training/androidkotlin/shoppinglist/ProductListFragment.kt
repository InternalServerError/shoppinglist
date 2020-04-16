package training.androidkotlin.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import training.androidkotlin.shoppinglist.Adapter.ProductListAdapter
import training.androidkotlin.shoppinglist.Entity.Product
import training.androidkotlin.shoppinglist.ViewModel.ProductWithCategoryViewModel

class ProductListFragment(val shoppingListId: Long?) : Fragment(), View.OnClickListener {
    private lateinit var rootView: View
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var view: LinearLayoutManager
    private val productWithProductsViewModel: ProductWithCategoryViewModel = ProductWithCategoryViewModel()
    private lateinit var productListRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_product_list, container, false)
        val listener = this
        productListAdapter = ProductListAdapter(emptyList(), listener)
        view = LinearLayoutManager(this.context)

        productWithProductsViewModel.getProducts(shoppingListId as Long)
            .observe(viewLifecycleOwner, Observer { lists ->
                productListRecyclerView =
                    rootView.findViewById<RecyclerView>(R.id.products_list).apply {
                        adapter = ProductListAdapter(lists, listener)
                        layoutManager = view
                    }
            })

        return rootView
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}
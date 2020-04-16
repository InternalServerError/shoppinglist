package training.androidkotlin.shoppinglist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import training.androidkotlin.shoppinglist.Entity.Relation.ProductWithCategory
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithProducts
import training.androidkotlin.shoppinglist.Entity.ShoppingList
import training.androidkotlin.shoppinglist.HomeFragment
import training.androidkotlin.shoppinglist.R

class ProductListAdapter(
    private val productsList: List<ProductWithCategory>,
    private val eventClickListener: View.OnClickListener
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    class ProductListViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val productCategory: TextView = item.findViewById(R.id.product_category)
        val productName: TextView = item.findViewById(R.id.product_name)
        val productQuantity: TextView = item.findViewById(R.id.product_quantity)
        val cardView: CardView = item.findViewById(R.id.product_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.products_list_item, parent, false)
        return ProductListViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = productsList[position]
        holder.cardView.tag = product.productId
        holder.productCategory.text = product.category?.name
        holder.productName.text = product.name
        holder.productQuantity.text = product.quantity.toString()
        holder.cardView.setOnClickListener(eventClickListener)
    }
}
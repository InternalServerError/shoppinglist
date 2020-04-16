package training.androidkotlin.shoppinglist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import training.androidkotlin.shoppinglist.Entity.Category
import training.androidkotlin.shoppinglist.Entity.Product
import training.androidkotlin.shoppinglist.R

class CategoryRecyclerViewAdapter(
    private val categoryList: List<Category>,
    private val productsList: MutableMap<String, MutableList<Product>>,
    private val eventClickListener: View.OnClickListener
) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryRecyclerViewViewHolder>() {
    class CategoryRecyclerViewViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val cardView: CardView = item.findViewById(R.id.new_shopping_list_categories_recycler_view_card)
        val categoryName: TextView = item.findViewById(R.id.new_shopping_list_categories_recycler_category_name)
        val productsCountBadge: TextView = item.findViewById(R.id.new_shopping_list_categories_recycler_view_badge_products_number)
        val addProductToCategoryButton: ImageButton = item.findViewById(R.id.new_shopping_list_categories_add_product)
        val tickToExpand: ImageView = item.findViewById(R.id.new_shopping_list_categories_recycler_view_tick_to_expand)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecyclerViewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.new_shopping_list_categories_recycler_view_item, parent, false)
        return CategoryRecyclerViewViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryRecyclerViewViewHolder, position: Int) {
        val category = categoryList[position]
        holder.cardView.tag = category.name
        holder.addProductToCategoryButton.tag = category.name
        holder.categoryName.text = category.name
        holder.productsCountBadge.text = if (
            productsList.containsKey(category.name)
            && productsList[category.name]?.size!! > 0
        ) {
            val products = productsList[category.name]
            var productQuantity = 0
            products?.forEach {
                productQuantity += it.quantity
            }
            productQuantity.toString()
        } else {
            "0"
        }
        holder.tickToExpand.setOnClickListener(eventClickListener)
        holder.addProductToCategoryButton.setOnClickListener(eventClickListener)
    }
}
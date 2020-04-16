package training.androidkotlin.shoppinglist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import training.androidkotlin.shoppinglist.Entity.Relation.ShoppingListWithProducts
import training.androidkotlin.shoppinglist.Entity.ShoppingList
import training.androidkotlin.shoppinglist.HomeFragment
import training.androidkotlin.shoppinglist.R

class ShoppingListAdapter(
    private val shoppingLists: List<ShoppingListWithProducts>,
    private val eventClickListener: View.OnClickListener
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {
    class ShoppingListViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val shoppingListTitle: TextView = item.findViewById(R.id.shopping_list_title)
        val shoppingListDescription: TextView = item.findViewById(R.id.shopping_list_description)
        val cardView: CardView = item.findViewById(R.id.shopping_list_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.shopping_list_item, parent, false)
        return ShoppingListViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return shoppingLists.size
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val shoppingList = shoppingLists[position]
        holder.cardView.tag = shoppingList.shoppingListId
        holder.shoppingListTitle.text = shoppingList.title
        holder.shoppingListDescription.text = shoppingList.products?.size.toString()
        holder.cardView.setOnClickListener(eventClickListener)
    }
}
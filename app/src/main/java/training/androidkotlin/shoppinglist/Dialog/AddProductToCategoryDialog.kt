package training.androidkotlin.shoppinglist.Dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import training.androidkotlin.shoppinglist.AddShoppingListFragment
import training.androidkotlin.shoppinglist.R

class AddProductToCategoryDialog(private val categoryName: String): DialogFragment() {
    private lateinit var dialogView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialogView = inflater
            .inflate(
                R.layout.add_category_product_dialog,
                container,
                false
            )

        val spinner = dialogView.findViewById<Spinner>(R.id.add_category_product_dialog_new_product_quantity)
        spinner.adapter = ArrayAdapter(
            dialogView.context,
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.product_quantities)
        )

        val submitButton: Button = dialogView.findViewById(R.id.add_category_product_dialog_new_product_submit)
        submitButton.setOnClickListener {
            sendResult()
        }

        return dialogView
    }

    private fun sendResult() {
        val addProductCategoryTextView = dialogView.findViewById<TextView>(R.id.add_category_product_dialog_new_product_name)
        val addProductCategoryQuantitySpinner = dialogView.findViewById<Spinner>(R.id.add_category_product_dialog_new_product_quantity)
        val intent = AddShoppingListFragment
            .newProductCategoryIntent(
                categoryName,
                addProductCategoryTextView.text.toString(),
                addProductCategoryQuantitySpinner.selectedItem.toString().toInt()
            )
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        dismiss()
    }
}
package training.androidkotlin.shoppinglist.Dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import training.androidkotlin.shoppinglist.AddShoppingListFragment
import training.androidkotlin.shoppinglist.R

class AddNewCategoryDialog(private val categories: List<String>) : DialogFragment() {
    private lateinit var dialogView: View

    interface AddNewCategoryDialogListener {
        fun onCancelButtonClick()
    }

    var listener: AddNewCategoryDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return dialogView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogView =
            activity?.layoutInflater?.inflate(R.layout.add_category_dialog, null, false)!!
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setPositiveButton("Ajouter") {
                _, _ -> sendResult()
        }
        builder.setNegativeButton("Annuler") {
                _, _ -> listener?.onCancelButtonClick()
        }
        val spinner = dialogView.findViewById<Spinner>(R.id.add_category_dialog_choose_category)

        val spinnerAdapter =
            ArrayAdapter(
                dialogView.context,
                R.layout.support_simple_spinner_dropdown_item,
                categories
            )
        spinner.adapter = spinnerAdapter

        return builder.create()
    }

    private fun sendResult() {
        val addCategoryTextView = dialogView.findViewById<TextView>(R.id.add_category_dialog_new_category_name)
        val categoryName = if (addCategoryTextView.text.isNotEmpty()) {
            addCategoryTextView.text.toString()
        } else {
            val spinner = dialogView.findViewById<Spinner>(R.id.add_category_dialog_choose_category)
            spinner.selectedItem.toString()
        }
        val intent = AddShoppingListFragment.newCategoryIntent(categoryName)
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        dismiss()
    }
}
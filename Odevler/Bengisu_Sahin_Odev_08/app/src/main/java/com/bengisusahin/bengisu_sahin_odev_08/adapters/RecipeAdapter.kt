package com.bengisusahin.bengisu_sahin_odev_08.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.bengisusahin.bengisu_sahin_odev_08.R
import com.bengisusahin.bengisu_sahin_odev_08.models.Recipe
import com.bengisusahin.bengisu_sahin_odev_08.view.DetailActivity

class RecipeAdapter(private val context: Activity, private val originalList: List<Recipe>) :
    ArrayAdapter<Recipe>(context, R.layout.recipe_row, originalList), Filterable {

    private var filteredList: List<Recipe> = originalList

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = convertView ?: context.layoutInflater.inflate(R.layout.recipe_row, parent, false)

        val dt = filteredList[position]

        val rowName = rootView.findViewById<TextView>(R.id.row_name)
        val rowCaloriesPerServing = rootView.findViewById<TextView>(R.id.row_caloriesPErServing)

        rowName.text = dt.name
        rowCaloriesPerServing.text = "${dt.caloriesPerServing} cal"

        rootView.setOnClickListener {
            startDetailActivity(filteredList[position])
        }
        return rootView
    }

    override fun getCount(): Int {
        return filteredList.size
    }

    override fun getItem(position: Int): Recipe? {
        return filteredList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private fun startDetailActivity(recipe: Recipe) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("detail", recipe)
        context.startActivity(intent)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchText = constraint?.toString()?.trim() ?: ""
                val filterResults = FilterResults()
                filterResults.values = if (searchText.isEmpty()) {
                    originalList
                } else {
                    originalList.filter { it.name.contains(searchText, ignoreCase = true) }
                }
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<Recipe>
                notifyDataSetChanged()
            }
        }
    }
}

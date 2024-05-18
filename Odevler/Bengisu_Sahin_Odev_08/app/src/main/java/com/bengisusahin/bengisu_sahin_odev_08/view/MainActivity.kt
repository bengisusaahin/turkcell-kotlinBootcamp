package com.bengisusahin.bengisu_sahin_odev_08.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.bengisusahin.bengisu_sahin_odev_08.R
import com.bengisusahin.bengisu_sahin_odev_08.adapters.RecipeAdapter
import com.bengisusahin.bengisu_sahin_odev_08.configs.ApiClient
import com.bengisusahin.bengisu_sahin_odev_08.databinding.ActivityMainBinding
import com.bengisusahin.bengisu_sahin_odev_08.models.Recipe
import com.bengisusahin.bengisu_sahin_odev_08.models.Recipes
import com.bengisusahin.bengisu_sahin_odev_08.services.IDummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var iDummyService: IDummyService
    private lateinit var binding: ActivityMainBinding
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeList: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        iDummyService = ApiClient.getClient().create(IDummyService::class.java)
        getRecipes()

        swipeRefreshData()

    }

    private fun getRecipes() {
        iDummyService.getRecipes().enqueue(object : Callback<Recipes> {
            override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                if (response.isSuccessful) {
                    // val recipesResponse = response.body()
                    // Log.d("API Response", recipesResponse.toString())
                    recipeList = response.body()!!.recipes
                    if (!recipeList.isNullOrEmpty()){
                        Log.d("arr", recipeList.toString())
                        recipeAdapter = RecipeAdapter(this@MainActivity, recipeList)
                        binding.listViewRecipes.adapter = recipeAdapter
                        binding.swipeRefreshLayout.isRefreshing = false
                    }else {
                        Toast.makeText(this@MainActivity, "No recipe found",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<Recipes>, throwable: Throwable) {
                Log.e("getRecipes", throwable.message!!)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Type the recipe."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchText ->
                    Log.d("onQueryTextSubmit", "Search text: $searchText")
                    recipeAdapter.filter.filter(searchText.trim())
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let { searchText ->
                    Log.d("onQueryTextChange", "Search text: $searchText")
                    recipeAdapter.filter.filter(searchText.trim())
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun swipeRefreshData() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            getRecipes()
        }
    }
}
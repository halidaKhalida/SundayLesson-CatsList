package uz.datatalim.myfavouritecat.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.datatalim.myfavouritecat.R
import uz.datatalim.myfavouritecat.adapter.BreedAdapter
import uz.datatalim.myfavouritecat.data.remote.ApiClient
import uz.datatalim.myfavouritecat.model.BreedsItem

class MainActivity : AppCompatActivity() {
    lateinit var adapter: BreedAdapter
    lateinit var breads: ArrayList<BreedsItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        breads = ArrayList()
        adapter = BreedAdapter()
        val rvBreed = findViewById<RecyclerView>(R.id.rv_breed)
        rvBreed.adapter = adapter
        loadBreeds()
        adapter.itemClick = { position ->
            val id = breads[position].id
            val intent = Intent(this, CatsActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

    }

    private fun loadBreeds() {
        ApiClient.apiService.getAllBreeds().enqueue(object : Callback<ArrayList<BreedsItem>> {
            override fun onResponse(
                call: Call<ArrayList<BreedsItem>>,
                response: Response<ArrayList<BreedsItem>>
            ) {
                if (response.isSuccessful) {
                    breads.clear()
                    breads.addAll(response.body()!!)
                    adapter.submitList(breads)
                }
            }

            override fun onFailure(call: Call<ArrayList<BreedsItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
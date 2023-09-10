package uz.datatalim.myfavouritecat.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.datatalim.myfavouritecat.R
import uz.datatalim.myfavouritecat.adapter.CatAdapter
import uz.datatalim.myfavouritecat.data.remote.ApiClient
import uz.datatalim.myfavouritecat.model.CatItem
import uz.datatalim.myfavouritecat.utils.EndlessRecyclerViewScrollListener

class CatsActivity : AppCompatActivity() {
    lateinit var adapter: CatAdapter
    var page = 0
    lateinit var images: ArrayList<CatItem>
    lateinit var loadingProgressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cats)

        initViews()
    }

    private fun initViews() {
        images = ArrayList()
        adapter = CatAdapter()
        val id = intent.getStringExtra("id")
        val rvCats = findViewById<RecyclerView>(R.id.rv_cat)
        loadingProgressBar = findViewById(R.id.loading)
        rvCats.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        rvCats.layoutManager = layoutManager
        loadImages(id!!)
        val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                loadImages(id)
            }
        }
        rvCats.addOnScrollListener(scrollListener)


    }

    private fun loadImages(id: String) {
        showLoading()
        ApiClient.apiService.getImagesByPage(id, getPagess())
            .enqueue(object : Callback<ArrayList<CatItem>> {
                override fun onResponse(
                    call: Call<ArrayList<CatItem>>,
                    response: Response<ArrayList<CatItem>>
                ) {
                    if (response.isSuccessful) {
                        adapter.submitList(response.body()!!)
                    }
                    hideLoading()
                }

                override fun onFailure(call: Call<ArrayList<CatItem>>, t: Throwable) {
                    hideLoading()
                }
            })
    }

    private fun getPagess(): Int {
        return page++
    }


    fun showLoading() {
        loadingProgressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        loadingProgressBar.visibility = View.GONE
    }
}
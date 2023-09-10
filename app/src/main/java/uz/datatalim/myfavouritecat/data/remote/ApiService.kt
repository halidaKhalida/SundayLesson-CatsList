package uz.datatalim.myfavouritecat.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.datatalim.myfavouritecat.model.BreedsItem
import uz.datatalim.myfavouritecat.model.CatItem

interface ApiService {


    @GET("breeds")
    fun getAllBreeds(): Call<ArrayList<BreedsItem>>

    @GET("images/search")
    fun getImagesByBreedId(
        @Query("breed_ids") breed_ids: String,
        @Query("limit") limit: Int = 10
    ): Call<ArrayList<CatItem>>

    @GET("images/search")
    fun getImagesByPage(
        @Query("breed_ids") breed_ids: String = "beng",
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 10
    ): Call<ArrayList<CatItem>>

}
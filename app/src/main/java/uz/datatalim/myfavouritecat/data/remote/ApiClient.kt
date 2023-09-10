package uz.datatalim.myfavouritecat.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val isTester = false
    private const val SERVER_PRODUCTION = "https://api.thecatapi.com/v1/"
    private const val SERVER_DEVELOPMENT = "https://api.thecatapi.com/v1/"

    private val baseURL = if (isTester) SERVER_DEVELOPMENT else SERVER_PRODUCTION
    private val client = getClient()


    private val retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.addHeader(
                "x-api-key",
                "live_zBOT8qO7NgAlvH8UWDismzsBIJqH0DQWkz7CiBwp4hseEIyhfjGgqb5e078lmsoW"
            )
            chain.proceed(builder.build())
        }).build()
    }

}
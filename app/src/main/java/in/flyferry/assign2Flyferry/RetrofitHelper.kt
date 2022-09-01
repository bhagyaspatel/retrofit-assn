package `in`.flyferry.assign2Flyferry

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//singleton object of interface
object RetrofitHelper {
    private const val BASE_URL = "https://quotable.io/"

    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
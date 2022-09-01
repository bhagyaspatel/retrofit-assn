package `in`.flyferry.assign2Flyferry

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesInterface {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page")page : Int) : Response<Quotes>

    //https://quotable.io/quotes?page=1
}


package `in`.flyferry.assign2Flyferry

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://quotable.io/"
interface QuotesInterface {

    @GET("quotes?")
    fun getQuotes(@Query("page")page : Int) : Call<Quotes>
    //https://quotable.io/quotes?page=1
}

//singleton object of interface
object QuotesObj{
    val quotesInstance : QuotesInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        quotesInstance = retrofit.create(QuotesInterface::class.java)
    }
}
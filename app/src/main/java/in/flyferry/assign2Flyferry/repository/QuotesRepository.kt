package `in`.flyferry.assign2Flyferry.repository

import `in`.flyferry.assign2Flyferry.Quotes
import `in`.flyferry.assign2Flyferry.QuotesInterface
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class QuotesRepository(private val quotesInterface: QuotesInterface) {

    private val quoteLivedata = MutableLiveData<Quotes>()

    val quotes : LiveData<Quotes>
    get() = quoteLivedata

    suspend fun getQuotes (page : Int){
        val myResult = quotesInterface.getQuotes(page)

        if(myResult.body() != null){
            quoteLivedata.postValue(myResult.body())
        }
    }
}
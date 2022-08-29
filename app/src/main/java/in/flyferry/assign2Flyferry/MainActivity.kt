package `in`.flyferry.assign2Flyferry

import `in`.flyferry.assign2Flyferry.adapters.QuotesAdapter
import `in`.flyferry.assign2Flyferry.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "API_CALL"
class MainActivity : AppCompatActivity() {

    lateinit var adapter : QuotesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.hide()
        }

        getQuotes()
    }

    private fun getQuotes() {
        val quotes = QuotesObj.quotesInstance.getQuotes(1)
        quotes.enqueue(object : Callback<Quotes>{
            override fun onResponse(call: Call<Quotes>, response: Response<Quotes>) {
                val quotes : Quotes? = response.body()
                if (quotes != null){
                    binding.progress.visibility = View.GONE
                    binding.quotesRV.visibility = View.VISIBLE
                    Log.d(TAG, "API call successful " + quotes.toString())
                    adapter = QuotesAdapter(this@MainActivity, quotes.results)
                    binding.quotesRV.adapter = adapter
                    binding.quotesRV.layoutManager = LinearLayoutManager(this@MainActivity)
                }
                else{
                    Log.d(TAG, "API call successful but quotes is null" )
                }
            }

            override fun onFailure(call: Call<Quotes>, t: Throwable) {
                Log.d(TAG, "error in API call " + t.message)
            }
        })
    }
}
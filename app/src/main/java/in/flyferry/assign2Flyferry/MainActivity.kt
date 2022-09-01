package `in`.flyferry.assign2Flyferry

import `in`.flyferry.assign2Flyferry.adapters.QuotesAdapter
import `in`.flyferry.assign2Flyferry.databinding.ActivityMainBinding
import `in`.flyferry.assign2Flyferry.repository.QuotesRepository
import `in`.flyferry.assign2Flyferry.viewModels.MainViewModel
import `in`.flyferry.assign2Flyferry.viewModels.MainViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

private const val TAG = "API_CALL"
class MainActivity : AppCompatActivity() {

    lateinit var adapter : QuotesAdapter
    lateinit var viewModel: MainViewModel

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

        val quotes = RetrofitHelper.getInstance().create(QuotesInterface::class.java)
        val repository = QuotesRepository(quotes)
        viewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        viewModel.quotes.observe(this) { quotes ->
            Log.d(TAG, "inside view modal")
            if (quotes != null) {
                binding.progress.visibility = View.GONE
                binding.quotesRV.visibility = View.VISIBLE
                Log.d(TAG, "API call successful " + quotes.toString())
                adapter = QuotesAdapter(this@MainActivity, quotes.results)
                binding.quotesRV.adapter = adapter
                binding.quotesRV.layoutManager = LinearLayoutManager(this@MainActivity)
            } else {
                Log.d(TAG, "API call successful but quotes is null")
            }
//            Log.d(TAG, "API call successfull " + quotes.toString())
        }

//        getQuotes()
    }

//    private fun getQuotes() {
//        val quotes = QuotesObj.quotesInstance.getQuotes(1)
//        quotes.enqueue(object : Callback<Quotes>{
//            override fun onResponse(call: Call<Quotes>, response: Response<Quotes>) {
//                val quotes : Quotes? = response.body()
//                if (quotes != null){
//                    binding.progress.visibility = View.GONE
//                    binding.quotesRV.visibility = View.VISIBLE
//                    Log.d(TAG, "API call successful " + quotes.toString())
//                    adapter = QuotesAdapter(this@MainActivity, quotes.results)
//                    binding.quotesRV.adapter = adapter
//                    binding.quotesRV.layoutManager = LinearLayoutManager(this@MainActivity)
//                }
//                else{
//                    Log.d(TAG, "API call successful but quotes is null" )
//                }
//            }
//
//            override fun onFailure(call: Call<Quotes>, t: Throwable) {
//                Log.d(TAG, "error in API call " + t.message)
//            }
//        })
//    }
}
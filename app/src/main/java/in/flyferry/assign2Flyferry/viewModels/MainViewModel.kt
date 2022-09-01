package `in`.flyferry.assign2Flyferry.viewModels

import `in`.flyferry.assign2Flyferry.Quotes
import `in`.flyferry.assign2Flyferry.repository.QuotesRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuotesRepository) : ViewModel() {
    init {
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            repository.getQuotes(1)
        }
    }

    val quotes : LiveData<Quotes>
    get() = repository.quotes
}
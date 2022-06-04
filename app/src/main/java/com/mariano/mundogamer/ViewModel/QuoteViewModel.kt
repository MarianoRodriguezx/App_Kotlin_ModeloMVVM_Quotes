package com.mariano.mundogamer.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariano.mundogamer.domain.GetQuotesUseCase
import com.mariano.mundogamer.domain.GetRandomQuoteUseCase
import com.mariano.mundogamer.model.QuoteModel
import com.mariano.mundogamer.model.QuoteProvider
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {
    val quoteModel = MutableLiveData<QuoteModel>()
    val quoteLiveData:LiveData<QuoteModel> = quoteModel
    var getQuotesUseCase = GetQuotesUseCase()
    val isLoading = MutableLiveData<Boolean>()
    var getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun randomQuote() {
        isLoading.postValue(true)

        val quote = getRandomQuoteUseCase()

        quote?.let {
            quoteModel.postValue(it)
        }

        isLoading.postValue(false)
    }

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()

            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }
}
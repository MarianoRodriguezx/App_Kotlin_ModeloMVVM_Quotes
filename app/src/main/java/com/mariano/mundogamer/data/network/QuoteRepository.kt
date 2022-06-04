package com.mariano.mundogamer.data.network

import com.mariano.mundogamer.model.QuoteModel
import com.mariano.mundogamer.model.QuoteProvider

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes():List<QuoteModel>{
        val response = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}
package com.mariano.mundogamer.domain

import com.mariano.mundogamer.data.network.QuoteRepository
import com.mariano.mundogamer.model.QuoteModel

class GetQuotesUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel>? = repository.getAllQuotes()
}
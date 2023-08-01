package com.example.domain.usecase.search

interface SearchFilePresentation {
    suspend fun searchFileContact(): Boolean
}
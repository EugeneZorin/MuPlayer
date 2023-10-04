package com.example.domain.repository.datastory

interface PlayerStateDt<T> {
    suspend fun getData(): List<T>

}
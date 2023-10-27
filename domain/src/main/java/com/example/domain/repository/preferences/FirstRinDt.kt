package com.example.domain.repository.preferences

interface FirstRinDt {
    fun isFirstRun(): Boolean
    fun setFirstRun(isFirstRun: Boolean)
}
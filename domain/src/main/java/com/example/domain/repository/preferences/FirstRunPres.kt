package com.example.domain.repository.preferences

interface FirstRunPres {
    fun isFirstRun(): Boolean
    fun setFirstRun(isFirstRun: Boolean)
}
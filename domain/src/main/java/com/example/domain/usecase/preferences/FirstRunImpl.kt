package com.example.domain.usecase.preferences

import com.example.domain.repository.preferences.FirstRinDt
import com.example.domain.repository.preferences.FirstRunPres

class FirstRunImpl(
    private val firstRinDt: FirstRinDt
): FirstRunPres {
    override fun isFirstRun(): Boolean {
        return firstRinDt.isFirstRun()
    }

    override fun setFirstRun(isFirstRun: Boolean) {
        firstRinDt.setFirstRun(isFirstRun)
    }
}
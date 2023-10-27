package com.example.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.repository.preferences.FirstRinDt
import javax.inject.Inject

class FirstRun @Inject constructor(
    context: Context,
): FirstRinDt {

    private val sharedPreferences =
        context.getSharedPreferences(SharedPreferencesNames.SHARED_CHECK, Context.MODE_PRIVATE)

    override fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(SharedPreferencesNames.FIRST_RUN, true)
    }

    override fun setFirstRun(isFirstRun: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(SharedPreferencesNames.FIRST_RUN, isFirstRun)
        editor.apply()
    }
}
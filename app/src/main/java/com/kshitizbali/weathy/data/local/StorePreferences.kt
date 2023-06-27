package com.kshitizbali.weathy.data.local

import android.content.Context
import androidx.preference.PreferenceManager

private fun saveStringToPreferences(context: Context, key: String, value: String) {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    sharedPreferences.edit().putString(key, value).apply()
}

fun saveLastLocation(context: Context, value: String) {
    val key = "last_location"

    saveStringToPreferences(context, key, value)
}

private fun getStringFromPreferences(context: Context, key: String): String? {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    return sharedPreferences.getString(key, null)
}

fun getLastLocation(context: Context): String? {
    val key = "last_location"

    return getStringFromPreferences(context, key)
}
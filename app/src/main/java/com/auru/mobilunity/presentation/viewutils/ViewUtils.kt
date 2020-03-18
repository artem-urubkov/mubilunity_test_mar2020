package com.auru.mobilunity.presentation.viewutils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

/**
 * Loading-Content-Error pattern result
 */
sealed class LCEResult {
    data class Success(val data: Any) : LCEResult()
    object Loading : LCEResult()
    data class Failure(val errorMessage: String) : LCEResult()
}
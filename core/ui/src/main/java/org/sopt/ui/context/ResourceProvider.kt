package org.sopt.ui.context

import androidx.annotation.StringRes


interface ResourceProvider {
    fun getString(@StringRes id: Int): String
}
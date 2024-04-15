package org.sopt.datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: String? = null,
    val pw: String? = null,
    val name: String? = null,
    val hobby: String? = null,
    val isAutoLogin: Boolean = false,
)

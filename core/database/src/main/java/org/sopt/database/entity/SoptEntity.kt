package org.sopt.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_sopt")
data class SoptEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val hobby: String,
)

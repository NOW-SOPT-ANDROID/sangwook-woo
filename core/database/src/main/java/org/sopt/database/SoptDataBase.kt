package org.sopt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sopt.database.entity.SoptEntity

@Database(
    entities = [
        SoptEntity::class
    ], version = 1
)
abstract class SoptDataBase : RoomDatabase() {
    abstract fun soptDao(): SoptDao
}
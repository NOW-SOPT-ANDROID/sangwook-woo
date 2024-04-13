package org.sopt.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.sopt.database.entity.SoptEntity

@Dao
interface SoptDao {
    @Query("SELECT * FROM table_sopt")
    fun getAll(): Flow<List<SoptEntity>>

    @Query("SELECT * FROM table_sopt WHERE name LIKE '%' || :input || '%'")
    fun getContainInput(input: String): Flow<List<SoptEntity>>

    @Insert
    suspend fun addFriend(soptEntity: SoptEntity)

    @Query("DELETE FROM table_sopt WHERE id = :id")
    suspend fun deleteFriendById(id: Int)

    @Query("DELETE FROM table_sopt")
    suspend fun deleteAll()
}
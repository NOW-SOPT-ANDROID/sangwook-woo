package org.sopt.domain.repo

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.sopt.model.ReqresUser

interface UserRepository {
    fun getUser(): Flow<PagingData<ReqresUser>>
}
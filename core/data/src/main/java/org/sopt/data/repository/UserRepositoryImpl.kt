package org.sopt.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.sopt.data.paging.UserPagingSource
import org.sopt.domain.repo.UserRepository
import org.sopt.model.ReqresUser
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userPagingSource: UserPagingSource,
) : UserRepository {
    override fun getUser(): Flow<PagingData<ReqresUser>> {
        return Pager(PagingConfig(pageSize = 6, prefetchDistance = 2)) {
            userPagingSource
        }.flow
    }
}
package org.sopt.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.sopt.model.ReqresUser
import org.sopt.network.api.ReqresApi
import org.sopt.network.model.response.toReqresUser
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserPagingSource @Inject constructor(
    private val reqresApi: ReqresApi,
) : PagingSource<Int, ReqresUser>() {
    override fun getRefreshKey(state: PagingState<Int, ReqresUser>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReqresUser> {
        return try {
            val currentPage = params.key ?: 1
            val users = toReqresUser(
                reqresApi.getUsers(
                    currentPage
                ).data
            )

            LoadResult.Page(
                data = users,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (users.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
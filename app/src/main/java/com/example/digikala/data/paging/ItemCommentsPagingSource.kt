package com.example.digikala.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.digikala.data.model.item_detail.Comment
import com.example.digikala.repository.ItemDetailRepository

class ItemCommentsPagingSource(
    private val repository: ItemDetailRepository,
    private val itemId: String
) : PagingSource<Int, Comment>() {
    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {

        return try {
            val pageNumber = params.key ?: 1
            val response = repository.getAllItemComments(
                pageNumber = pageNumber,
                pageSize = 5,
                itemId = itemId
            ).data

            LoadResult.Page(
                data = response!!,
                prevKey = null,
                nextKey = pageNumber + 1
            )

        } catch (e: Exception) {
            Log.e("3636", "error: $e")
            LoadResult.Error(e)
        }

    }
}
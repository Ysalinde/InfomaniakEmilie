package com.example.infomaniakemilie.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.infomaniakemilie.data.local.ShowDatabase
import com.example.infomaniakemilie.data.local.ShowEntity
import com.example.infomaniakemilie.data.mappers.toShowEntity
import retrofit2.HttpException
import java.io.IOException

/*
 *
 */
@OptIn(ExperimentalPagingApi::class)
class AllShowsRemoteMediator(
    private val showDb: ShowDatabase,
    private val mazeApi: MazeApi
): RemoteMediator<Int, ShowEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ShowEntity>
    ): MediatorResult {
        return try {
            val loadkey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1 else ((lastItem.id) / state.config.pageSize) + 1
                }
            }

            val shows = mazeApi.getShows(
                page = loadkey,
                pageSize = state.config.pageSize
            )

            /*
             * withTransaction is used beacause we insert a lot of data simultaneous
             * and we want to insert only when the transaction is successful
             * If it is not, "upsertAll" will not permit the insertion and the "if" will assure
             * that the DB is empty.
             */
            showDb.withTransaction {
                if(loadType == LoadType.REFRESH){
                    showDb.dao.clearAll()
                }
                val showEntities = shows.map {it.toShowEntity()}
                showDb.dao.upsertAll(showEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = shows.isEmpty()
            )
        } catch (e: IOException){
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }

    }
}
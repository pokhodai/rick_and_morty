package com.movie.rick_and_morty.tools

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING
}

sealed class ApiResult<out T>(val status: ApiStatus, val data: T?, val message: String?) {

    data class Success<out R>(private val _data: R?) : ApiResult<R>(
        status = ApiStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String) : ApiResult<Nothing>(
        status = ApiStatus.ERROR,
        data = null,
        message = exception
    )

    class Loading<out R> : ApiResult<R>(
        status = ApiStatus.LOADING,
        data = null,
        message = null
    )
}

inline fun <T> toResultFlow(crossinline call: suspend () -> Response<T>?): Flow<ApiResult<T>?> {
    return flow {
        emit(ApiResult.Loading())

        call()?.let { call ->
            try {
                if (call.isSuccessful) {
                    call.body()?.let { emit(ApiResult.Success(it)) }
                } else {
                    call.errorBody()?.let { responseBody ->
                        val error = responseBody.string()
                        responseBody.close()
                        emit(ApiResult.Error(error))
                    }
                }
            } catch (e: Exception) {
                emit(ApiResult.Error(e.toString()))
            }
        }

    }.flowOn(Dispatchers.IO)
}
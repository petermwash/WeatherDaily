package com.pemwa.weatherdaily.util

import kotlinx.coroutines.flow.*

/**
 * Handles the logic of the data will be fetched from local/remote
 */
inline fun <LOCAL, REMOTE> networkBoundResource(
    crossinline fetchLocal: () -> Flow<LOCAL>,
    crossinline fetchRemote: suspend () -> REMOTE,
    crossinline saveRemoteData: suspend (REMOTE) -> Unit,
    crossinline shouldFetchRemote: (LOCAL) -> Boolean = { true },
) = flow {

    val data = fetchLocal().first()

    val flow = if (shouldFetchRemote(data)) {
        emit(NetworkResult.Loading(data))

        try {
            saveRemoteData(fetchRemote())
            fetchLocal().map { NetworkResult.Success(it) }
        } catch (throwable: Throwable) {
            fetchLocal().map { NetworkResult.Error(throwable, it) }
        }
    } else {
        fetchLocal().map { NetworkResult.Success(it) }
    }

    emitAll(flow)
}
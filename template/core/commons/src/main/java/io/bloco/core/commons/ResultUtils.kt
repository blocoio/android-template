package io.bloco.core.commons

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.map

fun <T> Flow<T>.toResult(): Flow<Result<T>> =
    map { Result.success(it) }
        .catch { emit(Result.failure(it)) }

fun <T, R> Flow<Result<T>>.mapIfSuccess(mapper: suspend ((T) -> R)): Flow<Result<R>> =
    map { result -> result.map { mapper.invoke(it) } }

fun <T, R> Flow<Result<T>>.flatMapConcatIfSuccess(transform: suspend (value: T) -> Flow<R>): Flow<Result<R>> =
    map { result ->
        if (result.isSuccess) {
            transform(result.getOrThrow())
        } else {
            throw result.exceptionOrNull()!!
        }
    }.flattenConcat().toResult()

fun <T, R> Flow<Result<T>>.foldOnEach(
    onSuccess: suspend (value: T) -> R,
    onFailure: suspend (exception: Throwable) -> R
) =
    onEach { result ->
        result.fold(
            {
                onSuccess.invoke(it)
            }, {
                onFailure.invoke(it)
            }
        )
    }


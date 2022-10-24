package io.bloco.core.commons

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

@Suppress("FunctionName")
fun <E> PublishFlow() = MutableSharedFlow<E>(
    extraBufferCapacity = 1,
    onBufferOverflow = BufferOverflow.SUSPEND
)
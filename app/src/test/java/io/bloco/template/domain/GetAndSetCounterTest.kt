package io.bloco.template.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.bloco.template.data.models.Counter
import io.bloco.template.data.preferences.CounterStoragePreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAndSetCounterTest {

    val mockRepo = mock<CounterStoragePreferences>()
    val getAndSetCounter = GetAndSetCounter(mockRepo)

    @Test
    fun get() = runBlocking {
        val expected = Counter(5)
        whenever(mockRepo.getValue()).thenReturn(MutableStateFlow(expected))
        val result = getAndSetCounter.get().first()
        assertEquals(expected, result)
    }

    @Test
    fun editCounter() = runBlocking {
        val expected = Counter(5)
        val result = getAndSetCounter.editCounter(expected)
        verify(mockRepo).setValue(expected)
        assert(result.isSuccess)
        assertEquals(expected, result.getOrNull())
    }

    @Test
    fun editBellowZero() = runBlocking {
        val result = getAndSetCounter.editCounter(Counter(-1))
        assert(result.isFailure)
    }
}
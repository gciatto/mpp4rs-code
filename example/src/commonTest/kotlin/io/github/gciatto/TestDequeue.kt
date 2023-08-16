package io.github.gciatto

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class TestDequeue {
    @Test
    fun testCreationFromLiterals() {
        assertIs<Dequeue<Int>>(dequeOf(1, 2, 3))
    }

    @Test
    fun testCreationFromIterable() {
        assertIs<Dequeue<Int>>(dequeOf(setOf(1, 2, 3)))
    }

    @Test
    fun testCreationFromSequence() {
        assertIs<Dequeue<Int>>(dequeOf(sequenceOf(1, 2, 3)))
    }

    @Test
    fun testAppend() {
        assertEquals(
            expected = dequeOf(1, 2, 3, 4),
            actual = dequeOf(1, 2, 3).also { it.add(4) }
        )
    }

    @Test
    fun testPrepend() {
        assertEquals(
            expected = dequeOf(0, 1, 2, 3),
            actual = dequeOf(1, 2, 3).also { it.addFirst(0) }
        )
    }
}

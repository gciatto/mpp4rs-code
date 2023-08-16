@file:JvmName("Deque")

package io.github.gciatto

import kotlin.jvm.JvmName

typealias Dequeue<T> = MutableList<T>

expect fun <T> dequeOf(vararg items: T): Dequeue<T>

expect fun <T> dequeOf(items: Iterable<T>): Dequeue<T>

expect fun <T> dequeOf(items: Sequence<T>): Dequeue<T>

expect fun <T> Dequeue<T>.addFirst(item: T)

expect fun <T> Dequeue<T>.addFirst(items: Iterable<T>)

expect fun <T> Dequeue<T>.addFirst(items: Sequence<T>)

expect fun <T> Dequeue<T>.takeFirst(): T?

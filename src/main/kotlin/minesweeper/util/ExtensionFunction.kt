package minesweeper.util

import minesweeper.domain.position.Position

fun <T> Collection<Iterable<T>>.cartesianProduct(): List<List<T>> =
    if (isEmpty()) emptyList()
    else drop(1)
        .fold(first().map(::listOf)) { acc, iterable ->
            acc.flatMap { list ->
                iterable.map(list::plus)
            }
        }

fun Int.indexRange(): IntRange = if (this > 0) (0 until this) else IntRange.EMPTY

infix fun Int.comma(other: Int): Position = Position(this, other)

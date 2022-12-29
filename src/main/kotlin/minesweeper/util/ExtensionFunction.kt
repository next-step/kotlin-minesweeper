package minesweeper.util

fun <T> Collection<Iterable<T>>.cartesianProduct(): List<List<T>> =
    if (isEmpty()) emptyList()
    else drop(1)
        .fold(first().map(::listOf)) { acc, iterable ->
            acc.flatMap { list ->
                iterable.map(list::plus)
            }
        }

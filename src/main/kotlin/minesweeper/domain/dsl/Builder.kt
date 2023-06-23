package minesweeper.domain.dsl

fun interface Builder<T> {
    fun build(): T
}

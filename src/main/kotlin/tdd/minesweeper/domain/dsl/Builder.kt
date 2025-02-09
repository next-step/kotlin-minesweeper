package tdd.minesweeper.domain.dsl

interface Builder<T> {
    fun build(): T
}

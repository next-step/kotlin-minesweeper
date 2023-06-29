package minesweeper_refactor.domain.board.builder

@DslMarker
annotation class BoardDsl

@BoardDsl
abstract class DslBuilder<T> {

    abstract fun build(): T
}

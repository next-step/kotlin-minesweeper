package minesweeper.domain.block

sealed class Block(private val position: Position) {
    abstract fun isMines(): Boolean
}

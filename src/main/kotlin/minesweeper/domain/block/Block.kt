package minesweeper.domain.block

sealed class Block(val position: Position) {
    abstract fun isMines(): Boolean
}

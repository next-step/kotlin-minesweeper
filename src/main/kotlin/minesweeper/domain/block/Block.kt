package minesweeper.domain.block

sealed class Block(open val position: Position) {
    abstract val isMine: Boolean
}

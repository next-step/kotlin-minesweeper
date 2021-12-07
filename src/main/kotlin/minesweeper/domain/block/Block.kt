package minesweeper.domain.block

sealed class Block(open val position: Position) {
    abstract fun isMine(): Boolean

    abstract fun adjacentMineCount(): Int
}

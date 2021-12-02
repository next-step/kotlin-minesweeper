package minesweeper.domain.block

sealed class Block {
    abstract fun isMines(): Boolean
}

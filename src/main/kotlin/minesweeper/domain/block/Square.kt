package minesweeper.domain.block

sealed class Square {
    abstract fun isMines(): Boolean
}

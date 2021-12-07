package minesweeper.domain

sealed interface Cell {

    fun isMine(): Boolean
}

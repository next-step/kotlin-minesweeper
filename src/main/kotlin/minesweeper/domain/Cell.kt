package minesweeper.domain

sealed class Cell {
    fun isMine(): Boolean {
        return when (this) {
            is Mine -> true
            is Block -> false
        }
    }
}

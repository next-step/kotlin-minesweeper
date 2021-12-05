package minesweeper.domain.cell

sealed class Cell {

    fun isMine() = when (this) {
        is Block -> false
        is Mine -> true
    }
}

object Block : Cell()

object Mine : Cell()

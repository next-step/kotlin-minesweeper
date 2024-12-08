package minesweeper.domain

sealed class Cell

class Mine : Cell()

class Land : Cell() {
    private var _adjacentMines = 0

    val adjacentMines: Int
        get() = _adjacentMines

    fun updateAdjacentMines(adjacentCells: List<Cell>) {
        _adjacentMines = adjacentCells.count { it is Mine }
    }
}

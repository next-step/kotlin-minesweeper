package minesweeper.domain

sealed class Cell

class Mine : Cell()

class Land : Cell() {
    var adjacentMines = 0
        private set

    fun updateAdjacentMines(adjacentCells: List<Cell>) {
        this.adjacentMines = adjacentCells.count { it is Mine }
    }
}

package minesweeper.domain

sealed class Cell {

    var isOpened: Boolean = false
        private set

    fun open() {
        isOpened = true
    }

    fun noAdjacentMines(): Boolean {
        return this is Land && adjacentMines == 0
    }
}

class Mine : Cell()

class Land : Cell() {
    var adjacentMines = 0
        private set

    fun updateAdjacentMines(adjacentCells: List<Cell>) {
        this.adjacentMines = adjacentCells.count { it is Mine }
    }
}

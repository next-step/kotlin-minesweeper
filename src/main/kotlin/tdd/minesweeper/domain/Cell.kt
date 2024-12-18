package tdd.minesweeper.domain

abstract class Cell {
    private var isOpened: Boolean = false

    fun open() {
        isOpened = true
    }

    fun isOpened(): Boolean {
        return isOpened
    }

    fun noAdjacentMines(): Boolean {
        return this is Land && adjacentMineCount == 0
    }
}

class Mine : Cell()

class Land : Cell() {
    var adjacentMineCount: Int = 0
}

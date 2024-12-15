package tdd.minesweeper.domain

abstract class Cell {
    private var isOpened: Boolean = false

    fun open() {
        isOpened = true
    }

    fun isOpened(): Boolean {
        return isOpened
    }
}

class Mine : Cell()

class Land : Cell()

package minesweeper.model

class Cell(
    val row: Int = 0,
    val column: Int = 0,
) {
    var isMine: Boolean = false
        private set
    var mineAroundCount: Int = 0
        private set
    var isOpen: Boolean = false
        private set

    fun addMine() {
        isMine = true
    }

    fun addMineAroundCount() {
        mineAroundCount++
    }

    fun open() {
        isOpen = true
    }

    fun isAround(
        row: Int,
        column: Int,
    ): Boolean {
        return this.row == row && this.column == column
    }
}

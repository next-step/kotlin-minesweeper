package minesweeper.model


class Cell(
    val row: Int = 0,
    val column: Int = 0,
) {
    private var isMine: Boolean = false
    private var mineAroundCount: Int = 0

    fun isMine(): Boolean = isMine

    fun addMine() {
        isMine = true
    }

    fun addMineAroundCount() {
        mineAroundCount++
    }

    fun mineAroundCount(): Int = mineAroundCount

    fun isAround(row: Int, column: Int): Boolean {
        return this.row == row && this.column == column
    }
}

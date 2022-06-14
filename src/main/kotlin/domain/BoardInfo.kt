package domain

class BoardInfo(val height: Int, val width: Int, private val mines: Mines) {
    fun isMineAt(row: Int, col: Int): Boolean {
        return mines.contains(row, col)
    }
}
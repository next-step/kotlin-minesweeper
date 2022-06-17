package domain

class Board(val height: Int, private val width: Int, private val mines: Mines) {

    val rows = BoardRows(
        List(height) { row ->
            BoardRow(row, width, mines)
        }
    )

    init {
        fillNearMineCount()
    }

    private fun fillNearMineCount() {
        repeat(height) { row ->
            repeat(width) { col ->
                calcNearCount(row, col)
            }
        }
    }

    fun isMineAt(row: Int, col: Int): Boolean {
        return rows[row][col] is BoardItem.Mine
    }

    fun openAt(row: Int, col: Int) {
        rows[row][col].opened = true
    }

    private fun calcNearCount(row: Int, col: Int) {
        for (direction in Directions.values()) {
            val newRow = row + direction.row
            val newCol = col + direction.col


            if (isInside(newRow, newCol).not()) {
                return
            }

            val isMine = rows[newRow][newCol] is BoardItem.Mine

            if (isMine) {
                val boardItemAt = rows[row][col]
                boardItemAt.increaseCount()
            }
        }
    }

    private fun isInside(row: Int, col: Int): Boolean {
        val overUp = row < 0
        val overDown = row >= height
        val overLeft = col < 0
        val overRight = col >= width

        return (overUp || overDown || overLeft || overRight).not()
    }
}

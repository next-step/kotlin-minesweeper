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

    private fun calcNearCount(row: Int, col: Int) {
        for (direction in Directions.values()) {
            val newRow = row + direction.row
            val newCol = col + direction.col

            increaseIfMine(
                row,
                col,
                isInside(newRow, newCol) && rows[newRow][newCol] is BoardItem.Mine
            )
        }
    }

    private fun increaseIfMine(row: Int, col: Int, isMine: Boolean) {
        if (isMine) {
            rows[row][col].increaseCount()
        }
    }

    private fun isInside(row: Int, col: Int): Boolean {
        return (row < 0 || row >= height || col < 0 || col >= width).not()
    }
}

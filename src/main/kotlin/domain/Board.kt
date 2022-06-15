package domain

class Board(val boardHeight: Int, private val boardWidth: Int, private val mines: Mines) {

    val rows = BoardRows(
        List(boardHeight) { row ->
            BoardRow(row, boardWidth, mines)
        }
    )

    init {
        fillNearMineCount()
    }

    private fun fillNearMineCount() {
        repeat(boardHeight) { row ->
            repeat(boardWidth) { col ->
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
        return (row < 0 || row >= boardHeight || col < 0 || col >= boardWidth).not()
    }
}

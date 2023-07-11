package minesweeper.domain

object MineCounter {
    fun countNearByMines(board: List<List<GameBoardSquare>>, nowLocation: Pair<Int, Int>): Int {
        val (row, col) = nowLocation
        val directions = listOf(-1, 0, 1)
        return directions.sumBy { dx ->
            directions.sumBy { dy ->
                if (dx == 0 && dy == 0) {
                    0
                } else {
                    val newRow = row + dx
                    val newCol = col + dy
                    if (newRow in board.indices && newCol in board[newRow].indices && board[newRow][newCol].isMine()) {
                        1
                    } else { 0 }
                }
            }
        }
    }
}

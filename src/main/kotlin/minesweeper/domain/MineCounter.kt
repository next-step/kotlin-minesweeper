package minesweeper.domain

object MineCounter {

    fun initMineCounts(mutableBoard: MutableList<MutableList<GameBoardSquare>>): List<List<GameBoardSquare>> {
        val updatedBoard = mutableBoard.mapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, square ->
                val nowLocation = SquareLocation(rowIndex, columnIndex)
                countNumOfMineIfNotMine(square, mutableBoard, nowLocation)
            }.toList()
        }.toList()
        return updatedBoard
    }

    private fun countNumOfMineIfNotMine(
        square: GameBoardSquare,
        mutableBoard: MutableList<MutableList<GameBoardSquare>>,
        nowLocation: SquareLocation
    ): GameBoardSquare {
        if (!square.isMine()) {
            val numOfNearByMine = countNearByMines(mutableBoard, nowLocation)
            return GameBoardSquare.NumberSquare(numOfNearByMine)
        }
        return square
    }

    private fun countNearByMines(board: List<List<GameBoardSquare>>, nowLocation: SquareLocation): Int {
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
                    } else {
                        0
                    }
                }
            }
        }
    }
}

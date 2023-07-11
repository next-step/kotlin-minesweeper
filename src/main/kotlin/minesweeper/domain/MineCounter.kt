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
        return directions.flatMap { dx ->
            directions.map { dy -> SquareLocation(row + dx, col + dy) }
        }.filter { !isLocationEqualToInputLocation(it.x - row, it.y - col) }
            .count { newLocation ->
                isNewLocationInBoardBound(newLocation, board) && isNewLocationIsMine(
                    board,
                    newLocation
                )
            }
    }

    private fun isLocationEqualToInputLocation(dx: Int, dy: Int): Boolean {
        return dx == 0 && dy == 0
    }

    private fun isNewLocationInBoardBound(
        newLocation: SquareLocation,
        board: List<List<GameBoardSquare>>
    ) = newLocation.x in board.indices && newLocation.y in board[newLocation.x].indices

    private fun isNewLocationIsMine(
        board: List<List<GameBoardSquare>>,
        newLocation: SquareLocation
    ) = board[newLocation.x][newLocation.y].isMine()
}

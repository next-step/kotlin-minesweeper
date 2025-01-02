package domain

class MineSweeperGame(private val mineBoard: MineBoard) {
    fun isContinueGame(): Boolean {
        when {
            mineBoard.hasMineExploded() -> return false
            mineBoard.isCleared() -> return false
        }
        return true
    }

    fun openAdjacentCell(coordinate: Coordinate) {
        val queue = ArrayDeque<Coordinate>()
        queue.add(coordinate)

        while (queue.isNotEmpty()) {
            processCell(queue)
        }
    }

    private fun processCell(queue: ArrayDeque<Coordinate>) {
        val current = queue.removeFirst()
        val cell = mineBoard.getCell(current)

        if (cell.isOpened()) return

        mineBoard.openCell(current)

        if (shouldAddAdjacentCells(cell)) {
            val adjacentCoordinates = mineBoard.getAdjacentCoordinates(cell)
            queue.addAll(adjacentCoordinates)
        }
    }

    private fun shouldAddAdjacentCells(cell: Cell): Boolean {
        return cell is Cell.EmptyCell && mineBoard.countAdjacentMines(cell) == 0
    }

    fun getGameResult(): GameResult =
        when {
            mineBoard.isCleared() -> GameResult.SUCCESS
            else -> GameResult.FAILURE
        }
}

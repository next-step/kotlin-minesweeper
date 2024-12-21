package domain

class MineSweeperGame(private val mineBoard: MineBoard, var result: GameResult = GameResult.FAILURE) {
    fun isContinueGame(): Boolean {
        when {
            mineBoard.isAnyMineCellOpened() -> return false
            mineBoard.isAllEmptyCellsOpened() -> {
                result = GameResult.SUCCESS
                return false
            }
        }
        return true
    }

    fun openAdjacentCell(coordinate: Coordinate) {
        val cell = mineBoard.cells.get(coordinate)

        if (cell.isMineCell()) return
        if (cell.status == CellStatus.OPEN) return

        mineBoard.openCell(coordinate)

        if (cell is Cell.EmptyCell) {
            val adjacentCoordinates = mineBoard.getAdjacentCoordinates(coordinate)

            for (adjacentCoordinate in adjacentCoordinates) {
                openAdjacentCell(adjacentCoordinate)
            }
        }
    }
}

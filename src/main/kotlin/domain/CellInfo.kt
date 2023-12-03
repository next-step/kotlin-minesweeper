package domain

import domain.enums.CellType

class CellInfo(
    cellType: CellType = CellType.NOT_MINE,
    neighborMineCount: NeighborMineCount = NeighborMineCount(0),
    isOpened: Boolean = false
) {
    var cellType: CellType = cellType
        private set

    var neighborMineCount: NeighborMineCount = neighborMineCount
        private set

    var isOpened: Boolean = isOpened
        private set

    fun installMine() { cellType = CellType.MINE }

    fun isMine(): Boolean = cellType == CellType.MINE

    fun findNeighborMineCount(boardSettings: BoardSettings, board: List<CellList>, point: Point) {
        if (cellType == CellType.NOT_MINE) { neighborMineCount = NeighborMineCount(calculateNeighborMineCount(point, boardSettings, board)) }
    }

    private fun calculateNeighborMineCount(point: Point, boardSettings: BoardSettings, board: List<CellList>): Int {
        return point.getNeighborPoints()
            .filter { it.isValid(boardSettings.row, boardSettings.col) }
            .map { board[it.row].cells[it.col] }
            .count { it.cellInfo.isMine() }
    }

    fun isNotMine(): Boolean = cellType == CellType.NOT_MINE

    fun openCell() {
        isOpened = true
    }
}

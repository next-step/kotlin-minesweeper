package domain

import domain.enums.CellType

class CellInfo {
    var cellType: CellType = CellType.NOT_MINE
        private set

    var neighborMineCount: NeighborMineCount = NeighborMineCount(0)
        private set

    var isOpened: Boolean = false
        private set

    fun installMine() { cellType = CellType.MINE }

    fun isMine(): Boolean = cellType == CellType.MINE

    fun getNeighborMineCount(boardSettings: BoardSettings, board: List<CellList>, point: Point) {
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

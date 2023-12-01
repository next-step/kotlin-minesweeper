package domain

import domain.enums.CellType

class CellInfo(
    cellType: CellType = CellType.NOT_MINE,
    val neighborMineCount: NeighborMineCount = NeighborMineCount(0)
) {
    var cellType: CellType = cellType
        private set

    fun installMine() { cellType = CellType.MINE }

    fun isMine(): Boolean = cellType == CellType.MINE

    fun createCellInfo(boardSettings: BoardSettings, board: List<CellList>, point: Point): CellInfo {
        return if (cellType == CellType.MINE) { this }
        else { CellInfo(neighborMineCount = neighborMineCount.calculateNeighborMineCount(point, boardSettings, board)) }
    }
}

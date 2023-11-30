package domain

import domain.enums.CellType

class CellInfo(
    var cellType: CellType = CellType.NOT_MINE,
    val neighborMineCount: NeighborMineCount = NeighborMineCount(0)
) {
    fun installMine() {
        cellType = CellType.MINE
    }
}

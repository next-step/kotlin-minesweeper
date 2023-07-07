package minesweeper.domain

class Cell(
    var mineCount: Int = 0,
    var type: CellType
) {
    fun updateCellType(cellType: CellType) {
        this.type = cellType
    }
}

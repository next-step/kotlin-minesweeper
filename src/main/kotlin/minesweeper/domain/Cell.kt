package minesweeper.domain

class Cell(
    var type: CellType
) {
    fun updateCellType(cellType: CellType) {
        this.type = cellType
    }
}

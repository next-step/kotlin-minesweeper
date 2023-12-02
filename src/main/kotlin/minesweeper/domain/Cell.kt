package minesweeper.domain

class Cell(val type: CellType) {
    var aroundMineCount: Int = 0
}

package minesweeper

class Cell(
    val row: Row,
    val column: Column,
    var cellType: CellType = CellType.NONE,
)

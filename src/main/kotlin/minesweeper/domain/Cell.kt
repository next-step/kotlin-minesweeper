package minesweeper.domain

data class Cell(val type: CellType, val aroundMineCount: Int)

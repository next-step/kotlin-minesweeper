package minesweeper.domain

sealed class Cell {
    abstract val type: CellType
}

enum class CellType {
    MINE, EMPTY
}

data class MineCell(override val type: CellType = CellType.MINE) : Cell()

data class EmptyCell(override val type: CellType = CellType.EMPTY) : Cell()

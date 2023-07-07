package minesweeper.domain

sealed class CellType {
    class Mine : CellType()
    class Empty : CellType()
}

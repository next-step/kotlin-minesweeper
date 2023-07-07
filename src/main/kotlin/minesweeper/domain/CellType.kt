package minesweeper.domain

sealed class CellType {
    object Mine : CellType()
    object Empty : CellType()
}
